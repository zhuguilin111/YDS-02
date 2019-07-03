package com.yds.order.serviceimpl;

import com.yds.common.exception.ServiceException;
import com.yds.common.utils.PageUtil;
import com.yds.common.vo.PageObject;
import com.yds.order.dao.TableDao;
import com.yds.order.entity.Table;
import com.yds.order.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TableServiceImpl implements TableService {
    @Autowired
    private TableDao tableDao;

    @SuppressWarnings("rawtypes")
	@Override
    public PageObject findAll(Integer pageCurrent, Integer state) {
		//查询总记录数
		int rowsCount = tableDao.findCount(state);
		if (rowsCount == 0) {
			throw new ServiceException("系统没有查到对应记录");
		}
		//计算分页信息
		int pageSize = PageUtil.getPageSize();// 每页显示10条记录
		// 3.2)计算startIndex
		int startIndex = PageUtil.getStartIndex(pageCurrent);
		List<Table> records = tableDao.findAll(startIndex,pageSize,state);
        PageObject<Table> pageObject = PageUtil.<Table>packaging(records, pageSize, rowsCount, pageCurrent);
        return pageObject;
    }


    @Override
    @Transactional
    public void saveObject(Table entity) {
        if (entity.getTabnum() == null)
            throw new ServiceException("桌号不能为空");
        if(entity.getArea()==null)
            throw new ServiceException("所属区域不能为空");
        entity.setState(1);
        entity.setCreatedTime(new Date());
        tableDao.saveObject(entity);
    }

    @Override
    @Transactional
    public void updateState(Integer id,Integer state) {
        tableDao.updateState(id,state);
    }

    @Transactional
    @Override
    public void deleteObjects(Integer[] ids) {
        List<Integer> idsList = Arrays.asList(ids);
        tableDao.deleteObjects(idsList);
    }

    /** 根据id 查询桌位信息*/
	@Override
	public Table findObjectByTableId(Integer tableId) {
		
		Table table = tableDao.findObjectByTableId(tableId);
		if(table == null) {
			throw new ServiceException("没有相应的桌位!");
		}
//		if(table.getState() == 0) {
//			throw new ServiceException("这个桌位已经有人了");
//		}
		return table;
	}
}
