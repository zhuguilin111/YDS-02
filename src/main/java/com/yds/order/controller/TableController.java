package com.yds.order.controller;

import com.yds.common.vo.JsonResult;
import com.yds.order.entity.Table;
import com.yds.order.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;

    /** 向前端返回*/
    @RequestMapping("/doTableListUI")
    public String doTableListUI(){
        return "yds/table_list";
    }
    
    @RequestMapping("/{model}")
    public String doTableList(@PathVariable("model") String model){
        return "yds/"+model;
    }

    @RequestMapping("/doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(Integer pageCurrent, Integer state){
        return new JsonResult(tableService.findAll(pageCurrent,state));
    }

    @RequestMapping("/doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(Table entity){
        tableService.saveObject(entity);
        return new JsonResult("update OK");
    }

    @RequestMapping("/doStateById")
    @ResponseBody
    public JsonResult doStateById(Integer id,Integer state){
        tableService.updateState(id,state);
        return new JsonResult("update OK");
    }

    @RequestMapping("/doDeleteObjects")
    @ResponseBody
    public JsonResult doDeleteObjects(Integer[] ids){
        tableService.deleteObjects(ids);
        return new JsonResult("delete OK");
    }
}
