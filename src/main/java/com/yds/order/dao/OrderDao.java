package com.yds.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yds.order.entity.Order;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderDao {
    int insertObject(@Param("order") Order order);
    
    /** 查询总记录数*/
    int getRowsCount(@Param("tableId") Integer tableId,@Param("state")Integer state);
    
    /** 分页查询*/
    List<Order> findPageObjects(
			@Param("tableId") Integer tableId,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize,
			@Param("state")Integer state);
	
	/** 修改显示原先页面*/
	Order findObjectById(@Param("id") Integer id);
	
	/**更新数据*/ 
	int updateObject(@Param("order") Order order);
	
	/** 删除订单信息 doDeleteObject*/
	int deleteObject(@Param("id") Integer id);
	
	int getOrder(@Param("state")Integer state,@Param("id")Integer id);

	int findWeichuli();
}
