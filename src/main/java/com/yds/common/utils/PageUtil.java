package com.yds.common.utils;

import java.util.List;

import com.yds.common.vo.PageObject;

public class PageUtil<T> {
	private static int pageSize = 10;

	public static int getPageSize() {
		return pageSize;
	}

	public static int getStartIndex(Integer pageCurrent) {
		return (pageCurrent - 1) * pageSize;
	}

	// 4.对分页信息以及当前页记录进行封装
	// 4.1)构建PageObject对象
	// 4.2)封装数据
	public static <T> PageObject<T> packaging(List<T> records, int pageSize, int rowsCount, Integer pageCurrent) {
		PageObject<T> pageObject = new PageObject<T>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowsCount);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowsCount - 1) / pageSize + 1);
		return pageObject;
	}

}
