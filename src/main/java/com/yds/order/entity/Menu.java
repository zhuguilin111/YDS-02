package com.yds.order.entity;

import java.io.Serializable;
import java.util.Date;

public class Menu implements Serializable{

private static final long serialVersionUID = -1566263854382508245L;
private Integer id;
private Integer varietyId;
private String  name;
private String  varietyName;
private Double  price;
private String  note;
private String  src;
private Date    createdTime;
private Date    modifiedTime;

public String getVarietyName() {
	return varietyName;
}
public void setVarietyName(String varietyName) {
	this.varietyName = varietyName;
}
public Integer getVarietyId() {
	return varietyId;
}
public void setVarietyId(Integer varietyId) {
	this.varietyId = varietyId;
}
public Date getModifiedTime() {
	return modifiedTime;
}
public void setModifiedTime(Date modifiedTime) {
	this.modifiedTime = modifiedTime;
}
public String getSrc() {
	return src;
}
public void setSrc(String src) {
	this.src = src;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public Date getCreatedTime() {
	return createdTime;
}
public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
}
@Override
public String toString() {
	return "Menu [id=" + id + ", varietyId=" + varietyId + ", name=" + name + ", varietyName=" + varietyName
			+ ", price=" + price + ", note=" + note + ", src=" + src + ", createdTime=" + createdTime
			+ ", modifiedTime=" + modifiedTime + "]";
}
}
