package com.banxian.controller.dispCard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.banxian.controller.index.BaseController;
import com.banxian.entity.dispCard.IssuedInfoMap;
import com.banxian.plugin.PageView;
import com.banxian.util.Common;
import com.banxian.util.SysConsts;

/**
 * 
 * @author _wsq 2016-03-10
 * @version 2.0v
 */
@Controller
@RequestMapping("/cardReport/")
public class CardReportController extends BaseController {

	@RequestMapping("usedList")
	public String usedList(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dispCard/usedList";
	}
	
	@RequestMapping("unusedList")
	public String unusedList(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dispCard/unusedList";
	}
	
	/**
	 * 发卡报表
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("issuedList")
	public String issuedList(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dispCard/issuedList";
	}
	
	/**
	 * 发卡报表
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findIssuedData")
	public PageView findIssuedData(String pageNow, String pageSize)
			throws Exception {

		IssuedInfoMap issuedInfoMap = getFormMap(IssuedInfoMap.class);
		issuedInfoMap = toFormMap(issuedInfoMap, pageNow, pageSize);
		// 用户权限
		issuedInfoMap.put(SysConsts.ROLE_KEY,
				Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		issuedInfoMap.put(SysConsts.ORG_CODE,
				Common.findAttrValue(SysConsts.ORG_CODE));
		
		pageView.setRecords(IssuedInfoMap.mapper().findIssuedData(issuedInfoMap));

		return pageView;
	}

}