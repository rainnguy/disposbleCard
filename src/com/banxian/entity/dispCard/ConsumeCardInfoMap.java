package com.banxian.entity.dispCard;

import com.banxian.entity.base.FormMap;
import com.banxian.mapper.dispCard.DispCardMapper;
import com.banxian.util.SpringIocUtils;

public class ConsumeCardInfoMap extends FormMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7114206768250076216L;
	
	public static DispCardMapper mapper() {
		return SpringIocUtils.getBean(DispCardMapper.class);
	}
}
