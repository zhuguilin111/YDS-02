package com.yds.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yds.order.entity.Log;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LogDao {
	/**按条件查询日志总记录数
	 * @param username 查询条件
	 */
	int getRowCount(@Param("username")String username) ;
	/**查询日志*/
	List<Log> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize
			);
	/**删除日志*/
	int deleteObjectsById(@Param("ids")Integer[] ids);
	/**添加日志*/
	int insertObject(@Param("entity")Log entity);
}
