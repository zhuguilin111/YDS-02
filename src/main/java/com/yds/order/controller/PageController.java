package com.yds.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yds.common.vo.JsonResult;
import com.yds.order.entity.Table;
import com.yds.order.service.TableService;

@Controller
public class PageController {
	@Autowired
	private TableService tableService;
	
	private Integer tableId=0;
	@RequestMapping("doOrderUI")
	public String doOrderUI(Integer table) {
		//System.out.println(table);
		this.tableId = table;
		return "index";
	}
	
	@RequestMapping("doCheckTable")
	@ResponseBody
	public JsonResult doCheckTable() {
		Table table = tableService.findObjectByTableId(tableId);
		return new JsonResult(table);
	}
	
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}
	
	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}
	
	@RequestMapping("doPageUI")
	public String doPageUI(){//在加载log_list之前先加载分页信息页面
		 return "common/page";

	 }

	 @RequestMapping("/wellcome")
	public String wellCome(){
		return "wellcome";
	 }

}
