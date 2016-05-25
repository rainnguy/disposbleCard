package com.banxian.controller.dispCard;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;

import com.banxian.annotation.SystemLog;
import com.banxian.controller.index.BaseController;
import com.banxian.entity.dispCard.CreateCardInfoMap;
import com.banxian.entity.dispCard.DispCardMap;
import com.banxian.entity.dispCard.SpecInfoMap;
import com.banxian.exception.SystemException;
import com.banxian.mapper.dispCard.DispCardMapper;
import com.banxian.plugin.PageView;
import com.banxian.util.Common;
import com.banxian.util.DateUtil;
import com.banxian.util.JsonUtils;
import com.banxian.util.POIUtils;
import com.banxian.util.SysConsts;

/**
 * 礼品卡券管理
 * 
 * @author _wsq 2016-05-16
 * @version 2.0v
 */
@Controller
@RequestMapping("/disposableCard/")
public class DispCardManageController extends BaseController {
	
	@Inject
	private DispCardMapper dispCardMapper;
	
	/**
	 * 待生成的礼品卡页面
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("create")
	public String create(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dispCard/create";
	}
	
	/**
	 * 礼品卡生成
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("createCardPage")
	public String createCardPage(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dispCard/createCard";
	}
	
	/**
	 * 查询信息
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("searchInfo")
	public PageView searchInfo(String pageNow, String pageSize) throws Exception {

		DispCardMap dispCardMap = getFormMap(DispCardMap.class);
		dispCardMap = toFormMap(dispCardMap, pageNow, pageSize);
		// 用户权限
		dispCardMap.put(SysConsts.ROLE_KEY, Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		dispCardMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));
		
		// 当天日期
		dispCardMap.put("nowDate", DateUtil.getCurrDate2());
		
		pageView.setRecords(DispCardMap.mapper().findCreateCardInfo(dispCardMap));

		return pageView;
	}
	
	/**
	 * 导出待生成的卡信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportCreateInfoList")
	public void exportCreateInfoList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "待生成的卡";
		DispCardMap dispCardMap = findHasHMap(DispCardMap.class);
		String exportData = dispCardMap.getStr("exportData");// 列表头的json字符串

		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);

		List<DispCardMap> lis = dispCardMapper.findCreateCardInfo(dispCardMap);
		POIUtils.exportToExcel(response, listMap, lis, fileName);
	}
	
	/**
	 * 礼品卡生成处理
	 * 
	 * @param txtGroupsSelect
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@ResponseBody
	@RequestMapping("createCard")
	@SystemLog(module="礼品卡管理",methods="礼品卡管理-生成新卡")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)
	public String createCard() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		DispCardMap dispCardMap = getFormMap(DispCardMap.class);
		// 验证金额为空
		Object money = dispCardMap.get("money");
		if (money == null || "".equals(money.toString())) {
			return "nomoney";
		}
		// 验证数量为空
		Object number = dispCardMap.get("number");
		if (number == null || "".equals(number.toString())) {
			return "nonumber";
		}
		
		// 判断金额为数字
		if(!isNumeric(money.toString())){
			return "wrongmoney";
		}
		
		// 判断数量为数字
		if(!isNumeric(number.toString())){
			return "wrongnumber";
		}
		
		// 当前系统时间
		String nowdate = DateUtil.getCurrDate();
		
		// 当天日期
		String nowDay = DateUtil.getCurrDate3();
		
		CreateCardInfoMap createCardInfoMap = getFormMap(CreateCardInfoMap.class);
		// 当天的开始时间
		createCardInfoMap.put("startTime", DateUtil.getStartTime());
		// 当前系统时间
		createCardInfoMap.put("nowdate", nowdate);
		// 获取表中当天的最大卡号
		String maxCode = CreateCardInfoMap.mapper().findMaxCardId(createCardInfoMap);
		Integer tempCode = 0;
		if(maxCode != null && maxCode != ""){
			tempCode = Integer.valueOf(maxCode.substring(12));
		}
		Integer num = Integer.valueOf(number.toString());
		List<DispCardMap> dispCardMapList = new ArrayList<DispCardMap>();
		String zero = "00000000";
		for(int i = 1; i <= num ; i++){
			String str = String.valueOf(tempCode + i);
			String newCode = "LPK0" + nowDay + zero.substring(str.length()) + str;
			
			// 卡号
			dispCardMap.put("code", newCode);
			// 密码
			dispCardMap.put("password", encoderByMd5(newCode));
			// 站点编号
			dispCardMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));
			// 操作编号
			dispCardMap.put(SysConsts.OPER_CODE, Common.findAttrValue(SysConsts.OPER_CODE));
			// 当前系统时间
			dispCardMap.put("nowDate", nowdate);
			
			dispCardMapList.add(dispCardMap);
			
			dispCardMap = getFormMap(DispCardMap.class);
		}
		
		try {
			for(DispCardMap map : dispCardMapList){
				int count = DispCardMap.mapper().insertNewData(map);
				if(count == 0){
					return "insert0";
				}
			}
		} catch (Exception e) {
			throw new SystemException("生成卡异常");
		}
		return "success";
	}
	
	/**
	 * 礼品卡一览管理
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage")
	public String manage(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dispCard/manageCard";
	}
	
	/**
	 * 礼品卡一览管理
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findSpecificationData")
	public PageView findSpecificationData(String pageNow, String pageSize)
			throws Exception {

		SpecInfoMap specInfoMap = getFormMap(SpecInfoMap.class);
		specInfoMap = toFormMap(specInfoMap, pageNow, pageSize);
		// 用户权限
		specInfoMap.put(SysConsts.ROLE_KEY,
				Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		specInfoMap.put(SysConsts.ORG_CODE,
				Common.findAttrValue(SysConsts.ORG_CODE));
		
		pageView.setRecords(SpecInfoMap.mapper().findSpecificationData(specInfoMap));

		return pageView;
	}
	
	/**
	 * 导出礼品卡一览信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportManageInfoList")
	public void exportManageInfoList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "礼品卡一览";
		SpecInfoMap specInfoMap = findHasHMap(SpecInfoMap.class);
		String exportData = specInfoMap.getStr("exportData");// 列表头的json字符串

		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);

		List<SpecInfoMap> lis = dispCardMapper.findSpecificationData(specInfoMap);
		POIUtils.exportToExcel(response, listMap, lis, fileName);
	}
	
	/**
	 * 删除卡
	 * 
	 * @param txtGroupsSelect
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@ResponseBody
	@RequestMapping("deleteCard")
	@SystemLog(module="礼品卡管理",methods="礼品卡管理-删除卡")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)
	public String deleteCard(String cardId){
		
		DispCardMap dispCardMap = getFormMap(DispCardMap.class);
		dispCardMap.put(SysConsts.OPER_CODE, Common.findAttrValue(SysConsts.OPER_CODE));
		dispCardMap.put("cardId", cardId);
	
		try {
				int count = dispCardMapper.deleteData(dispCardMap);
				if(count == 0){
					return "delete0";
				}
		} catch (Exception e) {
			throw new SystemException("删除卡异常");
		}
		return "success";
	}
	
	/**
	 * 编辑卡
	 * 
	 * @param txtGroupsSelect
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@ResponseBody
	@RequestMapping("editCard")
	@SystemLog(module="礼品卡管理",methods="礼品卡管理-编辑卡")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)
	public String editCard(String cardId){
		
		DispCardMap dispCardMap = getFormMap(DispCardMap.class);
		dispCardMap.put(SysConsts.OPER_CODE, Common.findAttrValue(SysConsts.OPER_CODE));
//		dispCardMap.put("cardId", cardId);
		
		try {
			int count = dispCardMapper.editCardData(dispCardMap);
			if(count == 0){
				return "update0";
			}
		} catch (Exception e) {
			throw new SystemException("编辑卡异常");
		}
		
		return "success";
	}
	
	/**
	 * 编辑卡信息页面
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editCardPage")
	public String editCardPage(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dispCard/editCard";
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
     
     /**
      * 判断字符串是否为数字
      * 
      * @param str
      * @return
      */
     private static boolean isNumeric(String str){
    	 Pattern pattern = Pattern.compile("[0-9]*");
    	 return pattern.matcher(str).matches();
    }
}