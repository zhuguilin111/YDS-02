package com.yds.order.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yds.common.vo.EmpNode;
import com.yds.common.vo.PageObject;
import com.yds.order.entity.YdsEmp;

public interface EmpService {
	PageObject<EmpNode> findPageObjects(
			String username,
			Integer pageCurrent);
	//根据员工id
	Map<String, Object> doFindObjectById(Integer id);
	//保存添加数据
	int doSaveObject(YdsEmp entity,Integer[] roleIds);
	//修改员工信息
	int doUpdateObject(
					@Param("entity")YdsEmp entity,
					@Param("roleId")Integer[] roleIds);
	//禁用启用
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	//根据用户id删除员工信息
	int doDeleteObject(Integer id);
}
