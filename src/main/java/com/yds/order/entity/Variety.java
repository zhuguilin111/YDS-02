package com.yds.order.entity;

import java.io.Serializable;
import java.util.Date;

public class Variety implements Serializable {

	private static final long serialVersionUID = -4088375160538426822L;
    private Integer id;
    private String name;
    private Date createdTime;
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
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Override
	public String toString() {
		return "Variety [id=" + id + ", name=" + name + ", createdTime=" + createdTime + "]";
	}
}
