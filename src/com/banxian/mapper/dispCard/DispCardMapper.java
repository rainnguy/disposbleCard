package com.banxian.mapper.dispCard;

import java.util.List;

import com.banxian.entity.dispCard.ConsumeCardInfoMap;
import com.banxian.entity.dispCard.CreateCardInfoMap;
import com.banxian.entity.dispCard.DispCardMap;
import com.banxian.entity.dispCard.SpecInfoMap;
import com.banxian.mapper.base.BaseMapper;


public interface DispCardMapper extends BaseMapper{

	public List<DispCardMap> findIndexPage(DispCardMap dispCardMap);
	
	public List<SpecInfoMap> findSpecificationData(SpecInfoMap specInfoMap);
	
	public int consumeCard(ConsumeCardInfoMap consumeCardInfoMap);
	
	public ConsumeCardInfoMap findByCardId(ConsumeCardInfoMap conCardInfoMap);
	
	public int issueCard(DispCardMap dispCardMap);
	
	public String findMaxCardId(CreateCardInfoMap createCardInfoMap);
	
	public List<DispCardMap> findCreateCardInfo(DispCardMap dispCardMap);
	
	public int insertNewData(List<DispCardMap> list);
	
	public void changeStatus();
	
	public int deleteData(DispCardMap dispCardMap);
	
	public int editCardData(DispCardMap dispCardMap);
	
	public DispCardMap findEditCardInfo(DispCardMap dispCardMap);
}
