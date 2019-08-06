package com.yds.order.service;

import com.alipay.api.AlipayApiException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AlipayService {
    void ali(String total,String content,Integer tableId,HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, Exception;
}
