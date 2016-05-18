package com.banxian.entity.dispCard;

import com.banxian.entity.base.FormMap;
import com.banxian.mapper.dispCard.CardReportMapper;
import com.banxian.util.SpringIocUtils;

public class UsedInfoMap extends FormMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5380429440060001440L;

	public static CardReportMapper mapper() {
		return SpringIocUtils.getBean(CardReportMapper.class);
	}
}
