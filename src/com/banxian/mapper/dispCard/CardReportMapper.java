package com.banxian.mapper.dispCard;

import java.util.List;

import com.banxian.entity.dispCard.IssuedInfoMap;
import com.banxian.mapper.base.BaseMapper;

public interface CardReportMapper extends BaseMapper {

	public List<IssuedInfoMap> findIssuedData(IssuedInfoMap issuedInfoMap);
}
