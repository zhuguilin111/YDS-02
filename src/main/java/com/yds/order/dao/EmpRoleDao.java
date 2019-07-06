package com.yds.order.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EmpRoleDao {
	//通过员工id查询其对应的职位id
	Integer findRoleIdByEmpId(Integer empId);
	//根据员工的id添加关系表数据
	int insertObjects(
			@Param("empId")Integer empId,
			@Param("roleIds")Integer[] roleIds);
	//根据员工id删除员工与职位关系数据
	int deleteObjectsByEmpId(Integer empId);
}
