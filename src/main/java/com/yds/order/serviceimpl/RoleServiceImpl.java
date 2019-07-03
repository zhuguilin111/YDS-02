package com.yds.order.serviceimpl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yds.common.exception.ServiceException;
import com.yds.order.dao.RoleDao;
import com.yds.order.dao.RoleFunmenuDao;
import com.yds.order.entity.YdsEmp;
import com.yds.order.entity.YdsRole;
import com.yds.order.service.RoleService;
import com.yds.common.vo.RoleFunmenuVo;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleFunmenuDao roleFunmenuDao;
	
	/**查询所有角色*/
	@Override
	public List<YdsRole> findPageObjects(String name) {
		return roleDao.findPageObjects(name);
	}

	/**
	 * 添加角色
	 */
	
	@SuppressWarnings("unused")
	@Override
	@Transactional
	public int addRole(YdsRole role, Integer[] funmenuIds) {
		//获取当前登录用户
		YdsEmp emp = (YdsEmp)SecurityUtils.getSubject().getPrincipal();
		String username = emp.getUsername();
		role.setModifiedUser(username);
		//参数校验
		if(role == null)
			throw new IllegalArgumentException("保存角色不能为空!");
		if(role.getName() == null || role.getName() == "")
			throw new IllegalArgumentException("角色名不能为空!");
		if(funmenuIds == null || funmenuIds.length == 0)
			throw new IllegalArgumentException("请问角色分配权限");
		int rows = 0 ;
		try {
			rows = roleDao.addRole(role);
		} catch (Exception e) {
			throw new ServiceException("保存异常");
		}
		//向角色功能菜单关系表中写入数据
		roleFunmenuDao.addRoleFunmenu(role.getId(), funmenuIds);
		
		return rows;
	}

	/** 根据角色id查询对应的角色功能菜单信息*/
	@Override
	public RoleFunmenuVo doFindObjectById(Integer id) {
		if(id < 1)
			throw new IllegalArgumentException("非法id " + id);
		RoleFunmenuVo rfv = roleDao.doFindObjectById(id);
		if(rfv == null)
			throw new ServiceException("没有相应记录！");
		return rfv;
	}

	/**修改角色*/
	@SuppressWarnings("unused")
	@Transactional
	@Override
	public int updateObject(YdsRole role,Integer[] funmenuIds) {
		YdsEmp emp = (YdsEmp)SecurityUtils.getSubject().getPrincipal();
		String username = emp.getUsername();
		role.setModifiedUser(username);
		if(role == null)
			throw new IllegalArgumentException("保存对象不能为空！");
		if(role.getName() == null || role.getName() =="")
			throw new IllegalArgumentException("保存角色名不能为空！");
		//删除以前的角色功能菜单关系
		roleFunmenuDao.doDeleteObjects(role.getId());
		roleFunmenuDao.addRoleFunmenu(role.getId(), funmenuIds);
		int rows = 0;
		try {
			rows = roleDao.doUpdateObject(role);
		} catch (Exception e) {
			throw new ServiceException("修改异常！");
		}
		return rows;
	}

	@Transactional
	@Override
	public int deleteObject(Integer id) {
		if(id < 1) 
			throw new IllegalArgumentException("非法id " + id);
		//删除角色功能菜单关系
		int rows = 0 ;
		try {
			roleFunmenuDao.doDeleteObjects(id);
			rows = roleDao.doDeleteObject(id);
		} catch (Exception e) {
			throw new ServiceException("删除异常！");
		}
		return rows;
	}

	@Override
	public List<YdsRole> doFindZTreeNodes() {
		List<YdsRole> role = roleDao.doFindZTreeNodes();
		return role;
	}

}
