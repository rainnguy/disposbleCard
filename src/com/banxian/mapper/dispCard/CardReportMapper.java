package com.banxian.mapper.dispCard;

import java.util.List;

import com.banxian.entity.dispCard.IssuedInfoMap;
import com.banxian.entity.dispCard.SummaryInfoMap;
import com.banxian.entity.dispCard.UnusedInfoMap;
import com.banxian.entity.dispCard.UsedInfoMap;
import com.banxian.mapper.base.BaseMapper;

public interface CardReportMapper extends BaseMapper {

	/** 获取发卡信息 */
	public List<IssuedInfoMap> findIssuedData(IssuedInfoMap issuedInfoMap);
	
	/** 获取消费信息 */
	public List<UsedInfoMap> findUsedData(UsedInfoMap usedInfoMap);
	
	/** 获取未消费信息 */
	public List<UnusedInfoMap> findUnusedData(UnusedInfoMap unusedInfoMap);
	
	/** 获取统计信息 */
	public List<SummaryInfoMap> findSummaryData(SummaryInfoMap summaryInfoMap);
}
