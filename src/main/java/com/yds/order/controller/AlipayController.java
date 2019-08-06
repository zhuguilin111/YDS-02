package com.yds.order.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yds.common.config.AlipayConfig;
import com.yds.order.entity.Order;
import com.yds.order.service.AlipayService;
import com.yds.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private OrderService orderService;

    private Integer tableId ;
    private String content;
    private String total;

    @RequestMapping("/alipay/pay")
    public void  alipaySum(String total,String content,Integer tableId,HttpServletRequest request, HttpServletResponse response) throws Exception{
        this.tableId = tableId;
        this.content = content;
        this.total = total;
        alipayService.ali(total,content,tableId,request,response);
    }

    @RequestMapping("/alipay/notifyUI")
    public String notifyPage(){
        return "alipay/notify";
    }

    @RequestMapping("/alipay/return")
    public String returnPage(){
        Order order = new Order();
        order.setTotal(Double.parseDouble(total));
        order.setTableId(tableId);
        order.setContent(content);
        order.setPayType("支付宝");
        System.out.println(order);
        orderService.insertObject(order);
        return "redirect:/doOrderUI?table="+tableId;
    }
}
