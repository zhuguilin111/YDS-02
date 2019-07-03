package com.yds.order.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
private static final long serialVersionUID = 7381338455831761352L;
private Integer id;
private Integer tableId;
private Integer tablenum;
private String content;
private String payType;
private Double total;
private  Date createdTime;

public Integer getTablenum() {
	return tablenum;
}
public void setTablenum(Integer tablenum) {
	this.tablenum = tablenum;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getTableId() {
	return tableId;
}
public void setTableId(Integer tableId) {
	this.tableId = tableId;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getPayType() {
	return payType;
}
public void setPayType(String payType) {
	this.payType = payType;
}
public Double getTotal() {
	return total;
}
public void setTotal(Double total) {
	this.total = total;
}
public Date getCreatedTime() {
	return createdTime;
}
public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
}
@Override
public String toString() {
	return "Order [id=" + id + ", tableId=" + tableId + ", content=" + content + ", payType=" + payType + ", total="
			+ total + ", createdTime=" + createdTime + "]";
}
} 
