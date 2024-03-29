package com.yds.order.dao;

import java.util.List;

import com.yds.order.entity.Funmenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RoleFunmenuDao {
	
	/**
	 * 添加角色功能菜单关系
	 * @param roleId   角色id
	 * @param funmenuIds  功能菜单id
	 * @return
	 */
	
	int addRoleFunmenu(@Param("roleId")Integer roleId,@Param("funmenuIds")Integer[] funmenuIds);
	
	List<Integer> doFindFunmenuByRoleId(@Param("roleId")Integer roleIds); 
	
	/** 根据角色id删除对应角色*/
	int doDeleteObjects(Integer roleId);
	
	int deleteObjectsByFunmenuId(Integer funmenuId);

	/**
	 * 根据角色查找对应的菜单
	 *
	 */
	List<Funmenu> findFunmenusByRoleId(@Param("roleIds") Integer[] roleIds);

}
