package com.banxian.mapper.dispCard;

import java.util.List;

import com.banxian.entity.dispCard.ConsumeCardInfoMap;
import com.banxian.entity.dispCard.DispCardMap;
import com.banxian.entity.dispCard.SpecInfoMap;
import com.banxian.mapper.base.BaseMapper;


public interface DispCardMapper extends BaseMapper{

	/** 获取首页的初始信息 */
	public List<DispCardMap> findIndexPage(DispCardMap dispCardMap);
	
	/** 获取礼品卡管理页面的信息 */
	public List<SpecInfoMap> findSpecificationData(SpecInfoMap specInfoMap);
	
	/** 消费处理 */
	public int consumeCard(ConsumeCardInfoMap consumeCardInfoMap);
	
	/** 获取要消费的卡的信息 */
	public ConsumeCardInfoMap findByCardId(ConsumeCardInfoMap conCardInfoMap);
	
	/** 发卡处理 */
	public int issueCard(DispCardMap dispCardMap);
	
	/** 获取表中当天的最大卡号 */
	public String findMaxCardId(DispCardMap dispCardMap);
	
	/** 生成的卡的信息 */
	public List<DispCardMap> findCreateCardInfo(DispCardMap dispCardMap);
	
	/** 插入新卡的数据 */
	public int insertNewData(List<DispCardMap> list);
	
	/** 检查过期卡,并把过期的卡状态改为过期 */
	public void changeStatus();
	
	/** 删除卡 */
	public int deleteData(DispCardMap dispCardMap);
	
	/** 编辑卡 */
	public int editCardData(DispCardMap dispCardMap);
	
	/** 获取要编辑的卡的信息 */
	public DispCardMap findEditCardInfo(DispCardMap dispCardMap);
}
