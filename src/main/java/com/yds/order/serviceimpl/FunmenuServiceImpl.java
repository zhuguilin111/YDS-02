package com.yds.order.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yds.common.exception.ServiceException;
import com.yds.common.vo.Node;
import com.yds.order.dao.FunmenuDao;
import com.yds.order.dao.RoleFunmenuDao;
import com.yds.order.entity.Funmenu;
import com.yds.order.service.FunmenuService;

@Service
public class FunmenuServiceImpl implements FunmenuService{

	@Autowired
	private FunmenuDao funmenuDao;
	@Autowired
	private RoleFunmenuDao roleFunmenuDao;
	
	@Override
	public List<Funmenu> doFindZtreeMenuNodes() {
		return funmenuDao.findZTree();
	}
	
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String,Object>> list=
				funmenuDao.findObjects();
		if(list==null||list.size()==0)
			throw new ServiceException("没有对应的功能信息");
		return list;

	}
	@Override
	@Transactional
	public int deleteObject(Integer id) {
		if(id==null||id<1)
			throw new IllegalArgumentException("请先选择");
		//2.基于id进行子元素查询
		int count = funmenuDao.getChildCount(id);
		if(count>0)
			throw new ServiceException("请先删除子功能");
		int rows = funmenuDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("此功能可能已经不存在");
		//4.删除角色,菜单关系数据
		roleFunmenuDao.deleteObjectsByFunmenuId(id);
		return rows;
	}
	@Override
	public List<Node> findZtreeMenuNodes() {
		List<Node> findZtreeGongNodes = funmenuDao.findZtreeGongNodes();
		return findZtreeGongNodes;
	}
	@Override
	public int saveObject(Funmenu funmenu) {
		if(funmenu==null)
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(funmenu.getName()))
			throw new ServiceException("名字不能为空");
		int rows=0;
		try {
			rows = funmenuDao.insertObject(funmenu);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存失败");
		}

		return rows;
	}
	@Transactional
	@Override
	public int updateObject(Funmenu funmenu) {
		//1.合法验证
		if(funmenu==null)
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(funmenu.getName()))
			throw new ServiceException("功能名不能为空");

		//2.更新数据
		int rows=funmenuDao.updateObject(funmenu);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		//3.返回数据
		return rows;
	}
}
