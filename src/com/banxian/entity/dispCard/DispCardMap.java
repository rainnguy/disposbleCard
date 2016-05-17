package com.banxian.entity.dispCard;

import com.banxian.annotation.TableSeg;
import com.banxian.entity.base.FormMap;
import com.banxian.mapper.dispCard.DispCardMapper;
import com.banxian.util.SpringIocUtils;





/**
 * user实体表
 */
@TableSeg(tableName = "disp_card", id = "id")
public class DispCardMap extends FormMap<String, Object> {

	/**
	 * @descript
	 * @author _wsq 2016-03-10
	 * @version 2.0v
	 */
	private static final long serialVersionUID = 1L;
	public static DispCardMapper mapper() {
		return SpringIocUtils.getBean(DispCardMapper.class);
	}
	
}
