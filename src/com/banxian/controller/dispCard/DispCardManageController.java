package com.banxian.controller.dispCard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.banxian.controller.index.BaseController;
import com.banxian.entity.dispCard.SpecInfoMap;
import com.banxian.plugin.PageView;
import com.banxian.util.Common;
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
	
	/**
	 * 礼品卡生成
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
	 * 礼品卡发放
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("issue")
	public String issue(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dispCard/issue";
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
	 *  礼品卡一览管理
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
}