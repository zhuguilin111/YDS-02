package com.yds.order.dao;

import com.yds.order.entity.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TableDao {
    int findCount(@Param("state") Integer state);
    
    List<Table> findAll(@Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize, @Param("state") Integer state);

    void saveObject(Table entity);

    void updateState(@Param("id")Integer id,@Param("state") Integer state);

    void deleteObjects(List<Integer> idsList);
 
    /** 根据id 查询桌位信息*/
	Table findObjectByTableId(@Param("id") Integer id);
	/**根据桌位号找id*/
	Integer fingIdByTablenum(@Param("tablenum") Integer tablenum);
}
