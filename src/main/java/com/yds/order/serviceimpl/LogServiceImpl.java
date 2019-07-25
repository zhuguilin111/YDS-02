package com.yds.order.serviceimpl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yds.common.exception.ServiceException;
import com.yds.common.utils.PageUtil;
import com.yds.common.vo.PageObject;
import com.yds.order.dao.LogDao;
import com.yds.order.entity.Log;
import com.yds.order.service.Logservice;
@Service
public class LogServiceImpl implements Logservice {
	@Autowired
	private LogDao logDao;
	@Override
	public PageObject<Log> findPageObjects(String username, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("页码不正确");
		//查询总记录数
		int rowsCount = logDao.getRowCount(username);
		if(rowsCount<1)
			throw new ServiceException("没有日志记录");
		//3.查询当前页要呈现的记录
		//3.1.定义pageSize
		int pageSize = PageUtil.getPageSize();
		//3.2.定义startIndex
		int startIndex = PageUtil.getStartIndex(pageCurrent);
		List<Log> records = logDao.findPageObjects(username, startIndex, pageSize);
		//4.对查询结果进行封装
		PageObject<Log> po = PageUtil.packaging(records, pageSize, rowsCount, pageCurrent);
		//5.返回结果
		return po;
	}
	@RequiresPermissions("yds:log")
	@Transactional
	@Override
	public int deleteObjectsById(Integer[] ids) {
		if(ids==null||ids.length==0)
			throw new ServiceException("请选择要删除的日志");
		int rows = logDao.deleteObjectsById(ids);
		if(rows<1)
			throw new ServiceException("没有要删除的日志");
		return rows;
	}

}
