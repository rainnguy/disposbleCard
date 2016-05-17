package com.banxian.controller.dispCard;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.banxian.annotation.SystemLog;
import com.banxian.controller.index.BaseController;
import com.banxian.entity.dispCard.DispCardMap;
import com.banxian.exception.SystemException;
import com.banxian.util.Common;

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
	 * 礼品卡发放
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("issueImpl")
	@SystemLog(module="礼品卡管理",methods="礼品卡发放")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)
	public String issueImpl(Model model) throws Exception {
		
		DispCardMap dispCardMap = getFormMap(DispCardMap.class);
		dispCardMap.save();
		
		return "success";
	}
	
	
	/**
	 * 礼品卡消费
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("pay")
	@Transactional(readOnly=false)
	@SystemLog(module="礼品卡管理",methods="礼品卡消费")//凡需要处理业务逻辑的.都需要记录操作日志
	public String pay(Model model) throws Exception {
		DispCardMap dispCardMap = getFormMap(DispCardMap.class);
		dispCardMap.update();
		return "success";
	}
	
	/**
	 * 礼品卡发放页面
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/user/add";
	}

	/**
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addEntity")
	@SystemLog(module="系统管理",methods="用户管理-新增用户")//凡需要处理业务逻辑的.都需要记录操作日志
	@Transactional(readOnly=false)
	public String addEntity(){
		try {
			DispCardMap dispCardMap = getFormMap(DispCardMap.class);
//			PasswordHelper passwordHelper = new PasswordHelper();
//			passwordHelper.encryptPassword(dispCardMap);
//			fillCommValeu(userFormMap);
			dispCardMap.save();
		} catch (Exception e) {
			throw new SystemException("添加账号异常");
		}
		return "success";
	}
}