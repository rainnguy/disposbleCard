package com.banxian.entity.dispCard;

import com.banxian.entity.base.FormMap;
import com.banxian.mapper.dispCard.DispCardMapper;
import com.banxian.util.SpringIocUtils;

public class SpecInfoMap extends FormMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4684236790397583904L;

	public static DispCardMapper mapper() {
		return SpringIocUtils.getBean(DispCardMapper.class);
	}
}
