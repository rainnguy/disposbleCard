package com.banxian.controller.dispCard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.banxian.controller.index.BaseController;
import com.banxian.entity.dispCard.IssuedInfoMap;
import com.banxian.entity.dispCard.SummaryInfoMap;
import com.banxian.entity.dispCard.UnusedInfoMap;
import com.banxian.entity.dispCard.UsedInfoMap;
import com.banxian.mapper.dispCard.CardReportMapper;
import com.banxian.plugin.PageView;
import com.banxian.util.Common;
import com.banxian.util.DateUtil;
import com.banxian.util.EhcacheUtils;
import com.banxian.util.JsonUtils;
import com.banxian.util.POIUtils;
import com.banxian.util.SysConsts;

/**
 * 
 * @author _wsq 2016-03-10
 * @version 2.0v
 */
@Controller
@RequestMapping("/cardReport/")
public class CardReportController extends BaseController {

	@Inject
	private CardReportMapper cardReportMapper;
	
	/**
	 * 可消费报表
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("unusedList")
	public String unusedList(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dispCard/unusedList";
	}
	
	/**
	 * 可消费报表
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findUnusedData")
	public PageView findUnusedData(String pageNow, String pageSize)
			throws Exception {

		UnusedInfoMap unusedInfoMap = getFormMap(UnusedInfoMap.class);
		unusedInfoMap = toFormMap(unusedInfoMap, pageNow, pageSize);
		// 用户权限
		unusedInfoMap.put(SysConsts.ROLE_KEY, Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		unusedInfoMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));
		
		pageView.setRecords(UnusedInfoMap.mapper().findUnusedData(unusedInfoMap));

		return pageView;
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
		issuedInfoMap.put(SysConsts.ROLE_KEY, Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		issuedInfoMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));
		
		pageView.setRecords(IssuedInfoMap.mapper().findIssuedData(issuedInfoMap));

		return pageView;
	}
	
	/**
	 * 已消费报表
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("usedList")
	public String usedList(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dispCard/usedList";
	}
	
	/**
	 * 已消费报表
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findUsedData")
	public PageView findUsedData(String pageNow, String pageSize)
			throws Exception {

		UsedInfoMap usedInfoMap = getFormMap(UsedInfoMap.class);
		usedInfoMap = toFormMap(usedInfoMap, pageNow, pageSize);
		// 用户权限
		usedInfoMap.put(SysConsts.ROLE_KEY, Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		usedInfoMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));
		
		pageView.setRecords(UsedInfoMap.mapper().findUsedData(usedInfoMap));

		return pageView;
	}
	
	/**
	 * 概况统计
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("summary")
	public String summary(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		Map<String, String> orgCodeMap = new HashMap<String, String>();
		// 设置默认的空值
		orgCodeMap.put("", "选择站点");
		@SuppressWarnings("unchecked")
		// 获取所有的站点
		List<Map<String, Object>> stationMap =  (List<Map<String, Object>>) EhcacheUtils.get(SysConsts.SYS_ORGA_DATA);

		// 用户权限
		String roleKey=Common.findAttrValue(SysConsts.ROLE_KEY);
		if("admin".equals(roleKey)){
			for(Map<String, Object> map : stationMap){
				String orgName = (String) map.get("orgName");
				String orgNum = (String) map.get("orgCode");
				orgCodeMap.put(orgNum, orgName);
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
					orgCodeMap.put(orgNum, orgName);
				}
			}
		}
		
		model.addAttribute("orgValue", orgCodeMap);
		
		return Common.BACKGROUND_PATH + "/system/dispCard/summary";
	}
	
	/**
	 *  概况统计
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findSummaryData")
	public SummaryInfoMap findSummaryData(Model model) throws Exception {

		SummaryInfoMap summaryInfoMap = getFormMap(SummaryInfoMap.class);
		SummaryInfoMap resultMap = new SummaryInfoMap();
		// 用户权限
		summaryInfoMap.put(SysConsts.ROLE_KEY, Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		summaryInfoMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));

		resultMap.put("summaryData", SummaryInfoMap.mapper().findSummaryData(summaryInfoMap));
		
		return resultMap;
	}

	/**
	 * 导出发卡报表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportIssuedList")
	public void exportIssuedList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "发卡报表_" + DateUtil.getCurrDate4();
		IssuedInfoMap issuedInfoMap = findHasHMap(IssuedInfoMap.class);
		// 用户权限
		issuedInfoMap.put(SysConsts.ROLE_KEY, Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		issuedInfoMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));
		
		String exportData = issuedInfoMap.getStr("exportData");// 列表头的json字符串

		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);

		List<IssuedInfoMap> lis = cardReportMapper.findIssuedData(issuedInfoMap);
		POIUtils.exportToExcel(response, listMap, lis, fileName);
	}
	
	/**
	 * 导出已消费报表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportUsedList")
	public void exportUsedList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "已消费报表_" + DateUtil.getCurrDate4();
		UsedInfoMap usedInfoMap = findHasHMap(UsedInfoMap.class);
		// 用户权限
		usedInfoMap.put(SysConsts.ROLE_KEY, Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		usedInfoMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));
		
		String exportData = usedInfoMap.getStr("exportData");// 列表头的json字符串

		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);

		List<UsedInfoMap> lis = cardReportMapper.findUsedData(usedInfoMap);
		POIUtils.exportToExcel(response, listMap, lis, fileName);
	}
	
	/**
	 * 导出可消费报表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportUnusedList")
	public void exportUnusedList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "可消费报表_" + DateUtil.getCurrDate4();
		UnusedInfoMap unusedInfoMap = findHasHMap(UnusedInfoMap.class);
		// 用户权限
		unusedInfoMap.put(SysConsts.ROLE_KEY, Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		unusedInfoMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));
		
		String exportData = unusedInfoMap.getStr("exportData");// 列表头的json字符串

		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);

		List<UnusedInfoMap> lis = cardReportMapper.findUnusedData(unusedInfoMap);
		POIUtils.exportToExcel(response, listMap, lis, fileName);
	}
	
	/**
	 * 导出概况统计
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportSummary")
	public void exportSummary(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "概况统计_" + DateUtil.getCurrDate4();
		SummaryInfoMap summaryInfoMap = findHasHMap(SummaryInfoMap.class);
		// 用户权限
		summaryInfoMap.put(SysConsts.ROLE_KEY, Common.findAttrValue(SysConsts.ROLE_KEY));
		// 用户所属站的编号
		summaryInfoMap.put(SysConsts.ORG_CODE, Common.findAttrValue(SysConsts.ORG_CODE));
		
		String exportData = summaryInfoMap.getStr("exportData");// 列表头的json字符串

		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);

		List<SummaryInfoMap> lis = cardReportMapper.findSummaryData(summaryInfoMap);
		POIUtils.exportToExcel(response, listMap, lis, fileName);
	}
}