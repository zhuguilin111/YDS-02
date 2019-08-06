package com.yds.order.serviceimpl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yds.common.config.AlipayConfig;
import com.yds.order.service.AlipayService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AlipayServiceImpl implements AlipayService {

    @Override
    public void ali(String total,String content,Integer tableId,HttpServletRequest request, HttpServletResponse response) throws AlipayApiException,Exception {
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
        String total_amount = total;
        alipayRequest.setTerminalInfo("易店餐饮");
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
                + "\"," + "\"subject\":\"" + "菜品" + "\"," + "\"body\":\"" + content + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //AlipayConfig.logResult(result);  //记录支付日志
        response.setContentType("txt/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(result);
    }
}
