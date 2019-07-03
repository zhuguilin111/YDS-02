package com.yds.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yds.common.vo.RoleFunmenuVo;
import com.yds.order.entity.YdsRole;
@Mapper
public interface RoleDao {
	/**
	 * 分页查询
	 * @param name 角色名
	 * @param startIndex  开始位置
	 * @param pageSize  每页数量
	 * @return
	 */
	List<YdsRole> findPageObjects(@Param("name")String name);
	
	/** 查询有多少记录*/
	int getRowCount();
	
	/**
	 * 添加角色
	 */
	int addRole(YdsRole role);
	
	
	/**根据角色id查询角色及对应的功能菜单*/
	RoleFunmenuVo doFindObjectById(Integer id);
	
	/** 修改角色*/
	int doUpdateObject(YdsRole role);
	
	/**删除角色*/
	@Delete("delete from yds_role where id = #{id}")
	int doDeleteObject(Integer id);
	
	List<YdsRole> doFindZTreeNodes();
	
	
}
