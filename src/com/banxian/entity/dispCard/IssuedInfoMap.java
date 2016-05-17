package com.banxian.entity.dispCard;

import com.banxian.entity.base.FormMap;
import com.banxian.mapper.dispCard.CardReportMapper;
import com.banxian.util.SpringIocUtils;

public class IssuedInfoMap  extends FormMap<String, Object>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6418901920558746347L;

	public static CardReportMapper mapper() {
		return SpringIocUtils.getBean(CardReportMapper.class);
	}
}
