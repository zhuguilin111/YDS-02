package com.yds.order.service;


import com.yds.common.vo.PageObject;
import com.yds.order.entity.Order;

public interface OrderService {
	/** 插入提交的订单*/
	int insertObject(Order entity);

	PageObject<Order> findPageObjects(Integer pageCurrent, Integer tableId,Integer state);
	Order findObjectById(Integer id);
	/** 更新插入提交的订单*/ 
	int updateObject(Order order);

	/** 删除订单信息 doDeleteObject*/
	int deleteObject(Integer id);
	
	public int getOrder(Integer state ,Integer id);
}
