package com.yds.order.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yds.common.config.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AlipayController {

    @RequestMapping("/alipaySum")
    public Object  alipaySum(Model model , String payables, String subject , String body, HttpServletResponse response) throws Exception{
        //获取初始化alipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,
                                                            AlipayConfig.merchant_private_key,"json",
                AlipayConfig.charset,AlipayConfig.alipay_public_key,AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //商户订单号
        String out_trade_no = format.format(new Date());
        //付款金额
        String total_amount = payables.replace(",","");
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
                + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println(result);
        AlipayConfig.logResult(result);  //记录支付日志
        response.setContentType("txt/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(result);
        return null;
    }
}
