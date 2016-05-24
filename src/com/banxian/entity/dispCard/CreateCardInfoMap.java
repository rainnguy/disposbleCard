package com.banxian.entity.dispCard;

import com.banxian.entity.base.FormMap;
import com.banxian.mapper.dispCard.DispCardMapper;
import com.banxian.util.SpringIocUtils;

public class CreateCardInfoMap extends FormMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3029948625702312719L;

	public static DispCardMapper mapper() {
		return SpringIocUtils.getBean(DispCardMapper.class);
	}
}
