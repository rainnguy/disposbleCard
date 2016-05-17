package com.banxian.mapper.dispCard;

import java.util.List;

import com.banxian.entity.dispCard.DispCardMap;
import com.banxian.mapper.base.BaseMapper;




public interface DispCardMapper extends BaseMapper{

	public List<DispCardMap> findUserPage(DispCardMap dispCardMap);
	
	public List<DispCardMap> findUserByAccName(DispCardMap dispCardMap);
	
}
