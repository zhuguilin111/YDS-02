package com.yds.order.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yds.common.annotation.RequestLog;
import com.yds.common.exception.ServiceException;
import com.yds.common.utils.PageUtil;
import com.yds.common.vo.EmpNode;
import com.yds.common.vo.PageObject;
import com.yds.common.vo.YdsEmpRoleVo;
import com.yds.order.dao.EmpDao;
import com.yds.order.dao.EmpRoleDao;
import com.yds.order.entity.YdsEmp;
import com.yds.order.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService{
	@Autowired
	private EmpDao empDao;
	@Autowired
	private EmpRoleDao empRoleDao;
	
	@SuppressWarnings({ "static-access", "rawtypes" })
	@RequestLog
	@Override
	public PageObject<EmpNode> findPageObjects(String username, Integer pageCurrent) {
		//1.数据合法性验证
				if(pageCurrent==null||pageCurrent<=0)
					throw new ServiceException("参数不合法");
				//2.依据条件获取总记录数
				int rowCount=empDao.getRowCount(username);
				if(rowCount==0)
					throw new ServiceException("记录不存在");
				//3.计算startIndex的值
				int pageSize=new PageUtil().getPageSize();
				int startIndex=(pageCurrent-1)*pageSize;
				//4.依据条件获取当前页数据
				List<EmpNode> records=empDao.findPageObjects(
						username, startIndex, pageSize);
				//5.封装数据
				PageObject<EmpNode> pageObject=new PageObject<>();
				pageObject.setPageCurrent(pageCurrent);
				pageObject.setRowCount(rowCount);
				pageObject.setPageSize(pageSize);
				pageObject.setRecords(records);
				pageObject.setPageCount((rowCount-1)/pageSize+1);
				return pageObject;
			}
	
	@Override
	public Map<String, Object> doFindObjectById(Integer id) {
		//异常处理
		if(id==null||id<=0)
			throw new ServiceException("参数数据不合法,userId="+id);
		YdsEmpRoleVo emp = empDao.doFindObjectById(id);
		if(emp==null)
			throw new ServiceException("此用户可能已经不存在");
		Integer roleId = empRoleDao.findRoleIdByEmpId(id);
		HashMap<String, Object> map = new HashMap<>();
		map.put("emp", emp);
		map.put("roleId", roleId);
		return map;
	}
	@RequiresPermissions("yds:staff")
	@Override
	@Transactional
	public int doSaveObject(YdsEmp entity, Integer[] roleIds) {
		//1.参数校验
				if(entity==null)
					throw new ServiceException("保存对象不能为空");
				if(StringUtils.isEmpty(entity.getUsername()))
					throw new ServiceException("用户名不能为空");
				if(StringUtils.isEmpty(entity.getPassword()))
					throw new ServiceException("密码不能为空");
				if(roleIds==null || roleIds.length==0)
					throw new ServiceException("至少要为用户分配角色");
				//2.对密码进行加密
				String salt=UUID.randomUUID().toString();
				SimpleHash sh=
						new SimpleHash("MD5", //algorithmName算法
								entity.getPassword(),//source 未加密的密码
								salt,//salt
								1);//hashIterations加密次数
				entity.setSalt(salt);
				entity.setPassword(sh.toHex());
				//3.将对象写入到数据库
				int rows=empDao.doSaveObject(entity);
				empRoleDao.insertObjects(entity.getId(), roleIds);
				//if(count>0)
			    //throw new ServiceException("关系数据保存失败");
				return rows;
	}
	@RequiresPermissions("yds:staff")
	@Override
	@Transactional
	public int doUpdateObject(YdsEmp entity, Integer[] roleIds) {
		//验证参数合法性
		if (entity==null) {
			throw new ServiceException("修改信息对象不能为空");
		}
		if (roleIds==null||roleIds.length==0) {
			throw new ServiceException("请确定给员工赋予职位");
		}
		//修改员工信息
		int row = empDao.doUpdateObject(entity);
		//删除该员工目前对应的职位
		empRoleDao.deleteObjectsByEmpId(entity.getId());
		//重新给员工赋予职位
		empRoleDao.insertObjects(entity.getId(), roleIds);
		return row;
	}
	//禁用启用
	@RequiresPermissions("yds:staff")
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		//参数校验
		if(id==null||id<1)
		    throw new IllegalArgumentException("id值无效");
			if(valid!=1&&valid!=0)
			throw new IllegalArgumentException("状态值无效");
		int row = empDao.validById(id, valid, modifiedUser);
		if (row==0) {
			throw new IllegalArgumentException("该员工信息可能已经不存在");
		}
		return row;
	}

	@Override
	@Transactional
	public int doDeleteObject(Integer id) {
		if (id==null||id<=0) {
			throw new ServiceException("请先选择");
		}
		int rows = empDao.doDeleteObject(id);
		//删除关系数据
		empRoleDao.deleteObjectsByEmpId(id);
		return rows;
	}
	}
	


