package com.banxian.mapper;

import java.util.List;

import com.banxian.entity.UserFormBean;
import com.banxian.mapper.base.BaseMapper;


public interface UserMapper extends BaseMapper{

	public List<UserFormBean> findUserPage(UserFormBean userFormMap);
	
}
