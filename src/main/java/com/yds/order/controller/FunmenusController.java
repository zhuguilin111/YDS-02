package com.yds.order.controller;

import com.yds.order.dao.RoleDao;
import com.yds.order.entity.YdsEmp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yds.common.vo.JsonResult;
import com.yds.order.entity.Funmenu;
import com.yds.order.service.FunmenuService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/funmenu/")
public class FunmenusController {

	@Autowired
	private FunmenuService funmenuService;

	@Autowired
	private RoleDao roleDao;
	
	@RequestMapping("/doFindZtreeMenuNodes")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes() {
		return new JsonResult(funmenuService.doFindZtreeMenuNodes());
	}
	
	@RequestMapping("doFunmenuListUI")
	public String doGongListUI() {
		return "yds/gong_list";
	}
	@RequiresPermissions("yds:sys")
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(){
		return new JsonResult(funmenuService.findObjects());
	}
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		funmenuService.deleteObject(id);
		return new JsonResult("删除成功");
	}
	@RequestMapping("doGongEditUI")
	public String doGongEditUI(){
		return "yds/gong_edit";
	}
	@RequestMapping("doFindZtreeGongNodes")
	@ResponseBody
	public JsonResult doFindZtreeGongNodes(){
		return new JsonResult(
				funmenuService.findZtreeMenuNodes());
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Funmenu funmenu){
		funmenuService.saveObject(funmenu);
		return new JsonResult("添加成功");
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Funmenu funmenu){
		funmenuService.updateObject(funmenu);
	    return new JsonResult("修改成功");
	}


	//根据用户id获取其对应的功能菜单
	@RequestMapping("findFunmenusByRoleId")
	@ResponseBody
	public JsonResult findFunmenusByRoleId(){
		YdsEmp emp = (YdsEmp) SecurityUtils.getSubject().getPrincipal();
		//根据用户id获取角色id
		Integer[] ids = (Integer[]) roleDao.findRoleByEmpId(emp.getId());

		//System.out.println(Arrays.toString(ids));
		List<Funmenu> funmenus = funmenuService.findFunmenusByRoleId(ids);
		return new JsonResult(funmenus);
	}
}
