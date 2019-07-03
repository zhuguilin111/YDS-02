package com.yds.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yds.common.vo.JsonResult;
import com.yds.order.dao.VarietyDao;
import com.yds.order.entity.Variety;
@Controller
@RequestMapping("/variety/")
public class VarietyController {
	@Autowired
	private VarietyDao varietyDao;
	
	/** 向顾客端页面菜单分类结果*/
	@RequestMapping("doGetVarietyObjects")
	@ResponseBody
	public JsonResult doGetVarietyObjects(){
		List<Variety> Objects = varietyDao.findVarietyObjects();
		
		return new JsonResult(Objects);
	 }
}
