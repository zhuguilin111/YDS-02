package com.yds.order.service;


import java.util.List;

import com.yds.common.vo.RoleFunmenuVo;
import com.yds.order.entity.YdsRole;


public interface RoleService {
	
	List<YdsRole> findPageObjects(String name);
	
	int addRole(YdsRole role ,Integer[] funmenuIds);
	
	RoleFunmenuVo doFindObjectById(Integer id);
	
	int updateObject(YdsRole role,Integer[] funmenuIds);
	
	int deleteObject(Integer id);
	
	List<YdsRole> doFindZTreeNodes();
}
