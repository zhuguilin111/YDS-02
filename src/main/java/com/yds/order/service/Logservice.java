package com.yds.order.service;

import com.yds.common.vo.PageObject;
import com.yds.order.entity.Log;

public interface Logservice {
	PageObject<Log> findPageObjects(String username,Integer pageCurrent);
	int deleteObjectsById(Integer[] ids);
}
