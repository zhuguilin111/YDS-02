package com.yds.order.controller;

import com.yds.order.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yds.common.vo.JsonResult;
import com.yds.common.vo.PageObject;
import com.yds.order.entity.Order;
import com.yds.order.service.OrderService;

@Controller
@RequestMapping("/order/")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private  OrderDao orderDao;

	/** 添加点击保存  */
	@RequestMapping("doInsertObject")
	@ResponseBody
	public JsonResult doInsertObject(Order entity) {
		entity.setPayType("现金支付");
		orderService.insertObject(entity);//数据插入订单表
		return new JsonResult("订单提交成功!");
	}
	
	/** 向前端返回 order_list*/
	@RequestMapping("doOrderListUI")
	public String doOrderListUI() {
		return "yds/order_list";
	}
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(Integer pageCurrent,Integer tableId,Integer state) {
		PageObject<Order> PageObjects = orderService.findPageObjects(pageCurrent,tableId,state);
		return new JsonResult(PageObjects);
	}
	/** 向前端返回 order_edit*/
	@RequestMapping("doOrderEditUI")
	public String doOrderEditUI() {
		return "yds/order_edit";
	}
	/** 根据id查订单信息*/
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		Order findPageObjectById = orderService.findObjectById(id);
		return new JsonResult(findPageObjectById);
	}
	/** 更新订单信息*/
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Order order) {
		int rows = orderService.updateObject(order);
		return new JsonResult(rows+"条订单修改完成!");
	}
	
	/** 删除订单信息 */
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		int rows = orderService.deleteObject(id);
		return new JsonResult(rows+"条订单删除完成!");
	}
	
	/**接单*/
	@RequestMapping("getOrder")
	@ResponseBody
	public JsonResult getOrder(Integer state,Integer  id) {
		int row = orderService.getOrder(state, id);
		return new JsonResult("已接"+ row +"单");
	}

	@RequestMapping("findWeichuli")
	@ResponseBody
	public JsonResult findWeichuli(){
		int wcl = orderDao.findWeichuli();
		return new JsonResult(wcl);
	}
}
