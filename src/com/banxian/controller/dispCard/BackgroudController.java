package com.banxian.controller.dispCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.banxian.controller.index.BaseController;
import com.banxian.entity.dispCard.DispCardMap;
import com.banxian.mapper.dispCard.DispCardMapper;
import com.banxian.plugin.PageView;
import com.banxian.util.Common;
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
		
		pageView.setRecords(DispCardMap.mapper().findIndexPage(dispCardMap));

		return pageView;
	}
	
	@RequestMapping("soldCard")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/dispCard/soldCard";
	}
	
	@RequestMapping("stationSelect")
	public String seletRole(Model model) throws Exception {
		Map<String, String> orgCodeMap = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> stationMap =  (List<Map<String, Object>>) EhcacheUtils.get(SysConsts.SYS_ORGA_DATA);//
		for(Map<String, Object> map : stationMap){
			String orgName = (String) map.get("orgName");
			String orgCode = (String) map.get("orgCode");
			orgCodeMap.put(orgCode, orgName);
		}
		model.addAttribute("orgValue", orgCodeMap);
		
		return Common.BACKGROUND_PATH + "/system/dispCard/stationSelect";
	}
	
	
}