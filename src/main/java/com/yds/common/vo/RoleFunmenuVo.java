package com.yds.common.vo;

import java.io.Serializable;
import java.util.List;

public class RoleFunmenuVo implements Serializable{
	private static final long serialVersionUID = -7420466322471199312L;
	/** 角色id*/
	private Integer id;
	/** 角色名称*/
	private String name;
	/**角色描述*/
	private String note;
	/** 角色所对应的功能菜单*/
	private List<Integer> funmenuIds;
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
	public List<Integer> getFunmenuIds() {
		return funmenuIds;
	}
	public void setFunmenuIds(List<Integer> funmenuIds) {
		this.funmenuIds = funmenuIds;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "RoleFunmenuVo [id=" + id + ", name=" + name + ", note=" + note + ", funmenuIds=" + funmenuIds + "]";
	}
	
	
	
}
