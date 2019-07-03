package com.yds.order.entity;
import java.io.Serializable;
import java.util.Date;

public class Table implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4301571904664360970L;
	private Integer id;
    private Integer state;
    private Date createdTime;
    private String area;
    private Integer tabnum;
	@Override
	public String toString() {
		return "Table [id=" + id + ", state=" + state + ", createdTime=" + createdTime + ", area=" + area + ", tabnum="
				+ tabnum + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getTabnum() {
		return tabnum;
	}
	public void setTabnum(Integer tabnum) {
		this.tabnum = tabnum;
	}
	
    
}
