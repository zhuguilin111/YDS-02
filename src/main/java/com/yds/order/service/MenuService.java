package com.yds.order.service;

import com.yds.common.vo.PageObject;
import com.yds.order.entity.Menu;

public interface MenuService {

	
	
	/** 实现分页查询的方法*/
	PageObject<Menu> findPageObjects(String name,Integer pageCurrent);
	int saveObject(Menu entity); 
	int updateObject(Menu entity);
	int deleteObject(Integer id);
}
