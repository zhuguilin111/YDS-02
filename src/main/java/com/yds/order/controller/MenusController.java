package com.yds.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yds.common.vo.JsonResult;
import com.yds.common.vo.PageObject;
import com.yds.order.dao.MenuDao;
import com.yds.order.entity.Menu;
import com.yds.order.service.MenuService;

@Controller
@RequestMapping("/menu/")
public class MenusController {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private MenuDao menuDao;
	
	//向前端显示 menu_list
	@RequestMapping("doMenuListUI")
	public String doMenuListUI() {
		return "yds/menu_list";
	}
	
	/** 向顾客端页面显示不分页的结果*/
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(String name){
		List<Menu> Objects = menuDao.findObjects(name);
		return new JsonResult(Objects);
	 }
	
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer pageCurrent,String name){//
		PageObject<Menu> pageObjects = menuService.findPageObjects(name,pageCurrent);
		return new JsonResult(pageObjects);
	 }
	
	
	/** 向前端显示 menu_edit页面*/
	@RequestMapping("doMenuEditUI")
	public String doMenuEditUI() {
		return "yds/menu_edit";
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Menu entity) {
		System.out.println(entity);
		int rows = menuService.saveObject(entity);
		return new JsonResult(rows+"行记录保存成功");
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Menu entity) {
		int rows = menuService.updateObject(entity);
		return new JsonResult(rows+"行记录修改成功");
	}
	/** 删除菜品*/
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		int rows = menuService.deleteObject(id);
		return new JsonResult(rows+"行记录删除成功");
	}
}