package com.yds.order.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yds.common.vo.JsonResult;
import com.yds.order.entity.YdsRole;
import com.yds.order.service.RoleService;

@Controller
@RequestMapping("/role/")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	/** 向前端返回 role_list*/
	@RequestMapping("doRoleListUI")
	public String doRoleListUI() {
		return "yds/role_list";
	}
	
	/** 查询所有角色*/
	@RequestMapping("doFindRoles")
	@ResponseBody
	@RequiresPermissions("yds:role")
	public JsonResult doFindPageObjects(String name) {
		return new JsonResult(roleService.findPageObjects(name));
	}
	
	/**跳转到角色编辑页面*/
	@RequestMapping("doRoleEditUI")
	public String doRoleEditUI() {
		return "yds/role_edit";
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(YdsRole role ,Integer[] menuIds) {
		return new JsonResult("保存" + roleService.addRole(role, menuIds) + "条数据成功!");
	}	
	/**根据id查询*/
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(roleService.doFindObjectById(id));
	}
	
	/** 根据id删除*/
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObjectById(Integer id) {
		return new JsonResult("已删除 "+ roleService.deleteObject(id) + "条数据");
	}
	
	/**修改角色*/
	@RequestMapping("doUpdateObject")
	@ResponseBody
	@RequiresPermissions("yds:role")
	public JsonResult doUpdateObject(YdsRole role ,Integer[] menuIds) {
		return new JsonResult("已修改" + roleService.updateObject(role,menuIds) + "条数据");
	}
	
	@RequestMapping("doFindZTreeNodes")
	@ResponseBody
	public JsonResult doFindZTreeNodes() {
		List<YdsRole> role = roleService.doFindZTreeNodes();
		return new JsonResult(role);
		
	}
//	private UsernamePasswordToken token;
//	@ResponseBody
//	@RequestMapping("doGetModiFieduser")
//	public String doGetModiFieduser(){
//		String username = token.getUsername();
//		System.out.println(username);
//		return username;
//	}
	
}
