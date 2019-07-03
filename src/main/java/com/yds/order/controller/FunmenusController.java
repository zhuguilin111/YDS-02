package com.yds.order.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yds.common.vo.JsonResult;
import com.yds.order.entity.Funmenu;
import com.yds.order.service.FunmenuService;

@Controller
@RequestMapping("/funmenu/")
public class FunmenusController {

	@Autowired
	private FunmenuService funmenuService;
	
	@RequestMapping("/doFindZtreeMenuNodes")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes() {
		return new JsonResult(funmenuService.doFindZtreeMenuNodes());
	}
	
	@RequestMapping("doFunmenuListUI")
	public String doGongListUI() {
		return "yds/gong_list";
	}
	@RequiresPermissions("yds:user:valid")
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
}
