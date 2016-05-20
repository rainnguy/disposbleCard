package com.banxian.mapper.dispCard;

import java.util.List;

import com.banxian.entity.dispCard.DispCardMap;
import com.banxian.entity.dispCard.SpecInfoMap;
import com.banxian.mapper.base.BaseMapper;


public interface DispCardMapper extends BaseMapper{

	public List<DispCardMap> findIndexPage(DispCardMap dispCardMap);
	
	public List<SpecInfoMap> findSpecificationData(SpecInfoMap specInfoMap);
	
}
