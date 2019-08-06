package com.yds.order.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yds.common.utils.PageUtil;
import com.yds.common.vo.PageObject;
import com.yds.common.exception.ServiceException;
import com.yds.order.dao.OrderDao;
import com.yds.order.dao.TableDao;
import com.yds.order.entity.Order;
import com.yds.order.service.OrderService;
import com.yds.websocket.WebSocket;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private TableDao tableDao;
	
	@Autowired
	private WebSocket webSocket;
	
	/** 插入提交的订单*/
	@Override
	@Transactional
	public int insertObject(Order entity) {
		if(entity==null)
			throw new ServiceException("菜单不能为空");
		if(StringUtils.isEmpty(entity.getContent()))
			throw new ServiceException("请输入内容");
		int rows = orderDao.insertObject(entity);
		tableDao.updateState(entity.getTableId(), 0);//修改相应桌位状态
		if(rows>0) {
			webSocket.sendMessage("您有新订单！");
		}
		return rows;
	}
	
	/** 分页查询*/
	@Override
	public PageObject<Order> findPageObjects(Integer pageCurrent, Integer tablenum,Integer state) {
		/**根据桌位号找id*/
		Integer tableId = tableDao.fingIdByTablenum(tablenum);
		
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("当前页码不正确");
		}
		//查询总记录数
		int rowsCount = orderDao.getRowsCount(tableId,state);
		if (rowsCount == 0) {
			//throw new ServiceException("系统没有查到对应记录");
		}
		//计算分页信息
		int pageSize = PageUtil.getPageSize();// 每页显示5条记录
		// 3.2)计算startIndex
		int startIndex = PageUtil.getStartIndex(pageCurrent);
		
		List<Order> records = orderDao.findPageObjects(tableId, startIndex, pageSize,state);
		PageObject<Order> pageObject = PageUtil.<Order>packaging(records, pageSize, rowsCount, pageCurrent);
		return pageObject;
	}
	
	@Override
	public Order findObjectById(Integer id) {
		if(id==null||id<1)
			throw new ServiceException("请选择修改哪个订单");
		Order fob = (Order) orderDao.findObjectById(id);
		return fob;
	}
	
	/** 保存更新的订单信息 */
	@Override
	@Transactional
	public int updateObject(Order order) {
		// 1.合法性验证
		if (order == null)
			throw new ServiceException("更新的对象不能为空");
		if (order.getId() == null)
			throw new ServiceException("id的值不能为空");
		int rows = orderDao.updateObject(order);
		if (rows == 0)
			throw new ServiceException("对象可能已经不存在");
		return 0;
	}
	
	/** 删除订单*/
	@Override
	@Transactional
	public int deleteObject(Integer id) {
		// 1.验证参数的合法性
		if (id == null || id < 1)
			throw new ServiceException("id的值不正确,id=" + id);
		Order order = orderDao.findObjectById(id);//根据订单id 找桌位id
		tableDao.updateState(order.getTableId(), 1);//修改相应桌位状态
		// 2.执行dao操作
		int rows = orderDao.deleteObject(id);
		if (rows == 0)
			throw new ServiceException("数据可能已经不存在");
		return rows;
	}

	@Override
	public int getOrder(Integer state, Integer id) {
		int row = 0 ;
		try {
			row = orderDao.getOrder(state, id);
		} catch (Exception e) {
			throw new ServiceException("接单异常");
		}
		return row;
	}
}
