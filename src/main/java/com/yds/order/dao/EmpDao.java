package com.yds.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yds.common.vo.EmpNode;
import com.yds.common.vo.YdsEmpRoleVo;
import com.yds.order.entity.YdsEmp;

@Mapper
public interface EmpDao {
	//分页查询员工信息
	List<EmpNode> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize
			);
	//查询用户记录总数
	int getRowCount(@Param("username")String username);
	//点击添加按钮跳转到员工编辑页面
	YdsEmpRoleVo doFindObjectById(@Param("empId")Integer empId);
	//添加员工信息
	int doSaveObject(YdsEmp entity);
	//保存修改的信息
	int doUpdateObject(YdsEmp entity);
	//禁用启用
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	//根据用户名查询用户
	YdsEmp findUserByUserName(String username);
	//根据id删除用户信息
	int doDeleteObject(Integer id);
	
}	
