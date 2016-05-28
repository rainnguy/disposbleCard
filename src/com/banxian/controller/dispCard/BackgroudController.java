package com.banxian.controller.dispCard;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;

import com.banxian.annotation.SystemLog;
import com.banxian.controller.index.BaseController;
import com.banxian.entity.dispCard.ConsumeCardInfoMap;
import com.banxian.entity.dispCard.DispCardMap;
import com.banxian.entity.dispCard.OrgSelectMap;
import com.banxian.exception.SystemException;
import com.banxian.mapper.dispCard.DispCardMapper;
import com.banxian.plugin.PageView;
import com.banxian.util.Common;
import com.banxian.util.DateUtil;
import com.banxian.util.EhcacheUtils;
import com.banxian.util.SysConsts;

/**
 * 礼品卡券管理
 * 
 * @author _wsq 2016-05-16
 * @version 2.0v
 */
@Controller
@RequestMapping("/index/")
public class BackgroudController extends BaseController {
	
	@Inject
	private DispCardMapper dispCardMapper;
	
	/**
	 * 礼品卡首页
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("indexPage")
	public String issue(Model model) throws Exception {
		
		DispCardMap dispCardMap = new DispCardMap();
		
		List<DispCardMap> cardInfoList = dispCardMapper.findByNames(dispCardMap);
		
		model.addAttribute("indexInfo", cardInfoList);
		
		return Common.BACKGROUND_PATH + "/system/dispCard/index";
	}
	
	/**
	 * 初始信息
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("indexInfo")
	public PageView indexInfo(String pageNow, String pageSize) throws Exception {

		DispCardMap dispCardMap = getFormMap(DispCardMap.class);
		dispCardMap = toFormMap(dispCardMap, pageNow, pageSize);
		// 用户权限
		dispCardMap.put(SysConsts.ROLE_KEY, Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		dispCardMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));
		
		// 判断过期的卡，并把过期的卡则变成过期状态
		checkOutdate();
		
		pageView.setRecords(DispCardMap.mapper().findIndexPage(dispCardMap));

		return pageView;
	}
	
	/**
	 * 消费处理
	 * 
	 * @param cardId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("consumeCard")
	@SystemLog(module="礼品卡管理",methods="礼品卡管理-消费")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)
	public String consumeCard(String cardId){
		
		ConsumeCardInfoMap conCardInfoMap = getFormMap(ConsumeCardInfoMap.class);
		conCardInfoMap.put("cardId", cardId);
		
		// 获取该卡的信息
		conCardInfoMap = ConsumeCardInfoMap.mapper().findByCardId(conCardInfoMap);
		
		// 当前卡的状态
		String status = conCardInfoMap.get("status").toString();
		// 当前卡的有效期
		String indate = conCardInfoMap.get("indate").toString();
		// 当前系统时间
		String nowdate = DateUtil.getCurrDate();
		if(!"1".equals(status) || 0 < nowdate.compareTo(indate)){
			return "cannot";
		}
		
		try {
			// 通过卡号生成密码，传入sql判断
			conCardInfoMap.put("pwd", encoderByMd5(conCardInfoMap.get("code").toString()));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		// 用户权限
		conCardInfoMap.put(SysConsts.ROLE_KEY, Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		conCardInfoMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));
		// 操作编号
		conCardInfoMap.put(SysConsts.OPER_CODE, Common.findAttrValue(SysConsts.OPER_CODE));
		// 当前系统时间
		conCardInfoMap.put("nowDate", nowdate);
		
		try {
			int count = ConsumeCardInfoMap.mapper().consumeCard(conCardInfoMap);
			if(count == 0){
				return "updates0";
			}
		} catch (Exception e) {
			throw new SystemException("消费异常");
		}
		return "success";
	}
	
	/**
	 * 发卡
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("soldCard")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/dispCard/soldCard";
	}
	
	/**
	 * 发卡处理
	 * 
	 * @param txtGroupsSelect
	 * @return
	 */
	@ResponseBody
	@RequestMapping("issueCard")
	@SystemLog(module="礼品卡管理",methods="礼品卡管理-发卡")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)
	public String issueCard(String txtGroupsSelect){
		
		// 验证可用站点为空
		if (txtGroupsSelect == null || "".equals(txtGroupsSelect)) {
			return "noUsableStation";
		}
		DispCardMap dispCardMap = getFormMap(DispCardMap.class);
		// 验证卡号为空
		Object code = dispCardMap.get("code");
		if(code == null || "".equals(code.toString())){
			return "noCode";
		}
		
		// 限制站点
		dispCardMap.put("useAbledStation", txtGroupsSelect);
		
		// 当前系统时间
		String nowdate = DateUtil.getCurrDate();
		// 获取当前日期的下一年日期
		String nextYearDate = DateUtil.getNextYearDate(nowdate);
		// 操作编号
		dispCardMap.put(SysConsts.OPER_CODE, Common.findAttrValue(SysConsts.OPER_CODE));
		// 当前系统时间
		dispCardMap.put("nowDate", nowdate);
		// 所属站点
		dispCardMap.put("issuedStation", Common.findAttrValue(SysConsts.ORG_CODE));
		// 当前系统日期
		dispCardMap.put("issuedDate", DateUtil.getDate(new Date()).replaceAll("/", "-"));
		// 截止日期
		dispCardMap.put("nextYearDate", nextYearDate);
		
		try {
			int count = DispCardMap.mapper().issueCard(dispCardMap);
			if(count == 0){
				return "updates0";
			}
		} catch (Exception e) {
			throw new SystemException("发卡异常");
		}
		return "success";
	}
	
	/**
	 * 发卡时供选择的站
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("stationSelect")
	public String stationSelect(Model model) throws Exception {
		
		model.addAttribute("res", findByRes());
		
		// 待选择的站的MAP
		Map<String, String> orgCodeMap = new HashMap<String, String>();
		// 已选择的站的MAP
		OrgSelectMap orgSelectMap = getFormMap(OrgSelectMap.class);
		// 定义已选择的站
		String useAbledStation = null;
		if(!orgSelectMap.isEmpty() && !orgSelectMap.get("useAbledStation").toString().isEmpty()){
			useAbledStation = orgSelectMap.get("useAbledStation").toString();
		}
		orgSelectMap.clear();
		
		// 所有站的MAP
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> stationMap =  (List<Map<String, Object>>) EhcacheUtils.get(SysConsts.SYS_ORGA_DATA);
		
		// 用户权限
		String roleKey=Common.findAttrValue(SysConsts.ROLE_KEY);
		// 根据用户权限设定待选择的站和已选择的站的值
		if("admin".equals(roleKey)){
			for(Map<String, Object> map : stationMap){
				String orgName = (String) map.get("orgName");
				String orgNum = (String) map.get("orgCode");
				if(useAbledStation == null || useAbledStation.indexOf(orgNum) < 0){
					orgCodeMap.put(orgNum, orgName);
				}else{
					orgSelectMap.put(orgNum, orgName);
				}
			}
		}else{
			// 用户所属站的编号
			String selfOrgCode=Common.findAttrValue(SysConsts.ORG_CODE);
			for(Map<String, Object> map : stationMap){
				//  当前记录的编号
				String orgNum = map.get("orgCode").toString();
				if(selfOrgCode.equals(orgNum)){
					// 当前记录的名称
					String orgName = map.get("orgName").toString();
					
					if(useAbledStation == null || useAbledStation.indexOf(orgNum) < 0){
						orgCodeMap.put(orgNum, orgName);
					}else{
						orgSelectMap.put(orgNum, orgName);
					}
				}
			}
		}
		model.addAttribute("orgValue", orgCodeMap);
		model.addAttribute("orgSelect", orgSelectMap);
		
		return Common.BACKGROUND_PATH + "/system/dispCard/stationSelect";
	}
	
	/**
	 * 检查过期卡,并把过期的卡状态改为过期
	 */
	private void checkOutdate(){
		try {
			dispCardMapper.changeStatus();
		} catch (Exception e) {
			throw new SystemException("检查过期卡发生异常");
		}
	}
	
	/**
     * MD5方式加密字符串(当作密码)
     * 
     * @param str
     *            要加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private static String encoderByMd5(String str)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        // 加密后的字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}