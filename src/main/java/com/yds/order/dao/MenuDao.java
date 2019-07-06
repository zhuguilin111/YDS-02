package com.yds.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yds.order.entity.Menu;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MenuDao {
	    List<Menu> findObjects(@Param("name") String name);
	 
		List<Menu> findPageObjects(@Param("name") String name,
				@Param("startIndex") Integer startIndex,
				@Param("pageSize") Integer pageSize);

	    /** 查询总记录数*/
	    int getRowsCount(@Param("name") String name);
		
		 /** 根据id 删除菜品*/
		int deleteObject(@Param("id") Integer id);
		
		//List<Node> findZtreeMenuNodes();
		
		int insertObject(@Param("entity")Menu entity);
		
		int updateObject(@Param("entity")Menu entity);
}
