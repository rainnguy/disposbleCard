package com.banxian.controller.dispCard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.banxian.controller.index.BaseController;
import com.banxian.entity.dispCard.DispCardMap;
import com.banxian.mapper.dispCard.DispCardMapper;
import com.banxian.util.Common;

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
	
	@ResponseBody
	@RequestMapping("indexInfo")
	public String indexInfo(ModelMap model) throws Exception {
		DispCardMap dispCardMap = getFormMap(DispCardMap.class);
		
		
		
		return "success";
	}
}