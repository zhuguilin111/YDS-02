package com.yds.common.vo;

import java.io.Serializable;

public class JsonResult implements Serializable{
	private static final long serialVersionUID = -4138033536625725437L;
	/**状态码*/
	private int state=1;//1 表示ok,0表示error
	/**状态码对应的消息*/
	private String message="Operating  ";
	/**页面上要具体呈现的数据*/
	private Object data;
	public int getState() {
		return state;
	}
	public JsonResult() {
	}
	public JsonResult(String message) {
		this.message=message;
	}
	public JsonResult(Object data) {
		this.data=data;
	}
	/**
	 * 一旦服务器抛出来异常信息，就会调用这个构造函数创建 JsonResult对象
	 * 因此 JsonResult对象中便没有封装 data，向浏览器返回的 result中 data就为null
	 * Uncaught TypeError: Cannot read property 'records' of null
	 * */
	public JsonResult(Throwable e) {
		this.state=0;
		this.message=e.getMessage();
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
