package com.yds.order.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yds.common.exception.ServiceException;
import com.yds.common.utils.PageUtil;
import com.yds.common.vo.PageObject;
import com.yds.order.dao.MenuDao;
import com.yds.order.entity.Menu;

import com.yds.order.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
 
	@Autowired
	private MenuDao menuDao;
	
	/** 实现分页查询的方法*/
	@Override
	public PageObject<Menu> findPageObjects(String name,Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("当前页码不正确");
		}
		//查询总记录数
		int rowsCount = menuDao.getRowsCount(name);
		if (rowsCount == 0) {
			throw new ServiceException("系统没有查到对应记录");
		}
		//计算分页信息
		int pageSize = PageUtil.getPageSize();// 每页显示5条记录
		// 3.2)计算startIndex
		int startIndex = PageUtil.getStartIndex(pageCurrent);
		
		List<Menu> records = menuDao.findPageObjects(name, startIndex, pageSize);
		PageObject<Menu> pageObject = PageUtil.<Menu>packaging(records, pageSize, rowsCount, pageCurrent);
		return pageObject;
	}
	/** 插入操作*/
	@Override
	public int saveObject(Menu entity) {
		if(entity == null) {
			throw new ServiceException("保存对象不能为空");
			}	
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("请输入菜名");
		int rows = menuDao.insertObject(entity);
		if(rows==0)
			throw new ServiceException("保存失败");
		return rows;
	}
    /** 更新操作*/
	@Override
	public int updateObject(Menu entity) {
		if(entity==null)
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("请输入菜名");
		int rows = menuDao.updateObject(entity);
		if(rows==0)
			throw new ServiceException("保存失败");
		return rows;
	}
	/** 删除操作*/
	@Override
	public int deleteObject(Integer id) {
		// 1.验证参数的合法性
		if (id == null || id < 1)
			throw new ServiceException("id的值不正确,id=" + id);
		// 2.执行dao操作
		int rows = menuDao.deleteObject(id);
		if (rows == 0)
			throw new ServiceException("数据可能已经不存在");
		return rows;
	}
}
