package com.yds.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yds.common.vo.JsonResult;
import com.yds.common.vo.PageObject;
import com.yds.order.entity.Log;
import com.yds.order.service.Logservice;

@Controller
@RequestMapping("/log/")
public class LogController {
	@Autowired
	private Logservice logservice;
	@RequestMapping("doLogListUI")
	public String doLogListUI() {
		return "yds/log_list";
	}
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		PageObject<Log> findPageObjects = logservice.findPageObjects(username, pageCurrent);
		return new JsonResult(findPageObjects);
	}
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer[] ids) {
		logservice.deleteObjectsById(ids);
		return new JsonResult("删除成功");
	}
}
