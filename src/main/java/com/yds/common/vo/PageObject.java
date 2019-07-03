package com.yds.common.vo;
/**
 * 负责封装业务层当前页记录及分页信息
 * */
import java.io.Serializable;
import java.util.List;

public class PageObject<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7316224976001986636L;
	
	private Integer pageCurrent=1;/**当前页的页码值*/
    private Integer pageSize=3; /**页面大小*/
    private Integer rowCount=0;/**总行数(通过查询获得)*/
    private Integer pageCount=0;/**总页数(通过计算获得)*/
    private List<T> records;/**当前页记录*/
    
	public Integer getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
	   this.pageCount = pageCount;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	} 

}
