package com.yds.service.realm;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yds.order.dao.EmpDao;
import com.yds.order.dao.EmpRoleDao;
import com.yds.order.dao.FunmenuDao;
import com.yds.order.dao.RoleFunmenuDao;
import com.yds.order.entity.YdsEmp;


/**借助此realm完成认证和授权信息的获取及封装*/
@Service
public class ShiroEmpRealm extends AuthorizingRealm{
	@Autowired
	private EmpDao empDao;
	/**设置凭证匹配器*/
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
		HashedCredentialsMatcher cMatcher=
				new HashedCredentialsMatcher();
		//设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		cMatcher.setHashIterations(1);
		super.setCredentialsMatcher(cMatcher);
	}
	/**负责认证信息的获取及封装*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//1.从token获取用户信息
		UsernamePasswordToken upToken=
		(UsernamePasswordToken)token;
		String username=upToken.getUsername();
		//2.基于用户名查询用户信息	
		YdsEmp emp = empDao.findUserByUserName(username);
		//3.校验用户信息(用户是否存在,是否禁用)
		if(emp==null)
			throw new UnknownAccountException();
		if(emp.getValid()==0)
		throw new LockedAccountException();
		//4.封装数据并返回
		ByteSource credentialsSalt=
		ByteSource.Util.bytes(emp.getSalt());
		
		SimpleAuthenticationInfo info=
		new SimpleAuthenticationInfo(
				emp,//principal 身份 
				emp.getPassword(),//hashedCredentials 已加密密码 
				credentialsSalt,//credentialsSalt 加密盐
				getName());//realmName
		System.out.println(info);
		return info;//返回给认证管理器
	}
	@Autowired
	private EmpRoleDao empRoleDao;
	@Autowired
	private RoleFunmenuDao roleFunmenuDao;
	@Autowired
	private FunmenuDao funmenuDao;
    /**此方法负责授权信息的获取及封装*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principals) {
		//1.获取用户id
		YdsEmp emp=(YdsEmp) principals.getPrimaryPrincipal();
		//2.基于用户id查询角色id
		Integer roleId = empRoleDao.findRoleIdByEmpId(emp.getId());
		if(roleId<=0)
			throw new AuthorizationException();
		//3.基于角色id获取菜单id
		Integer[] array={};
		List<Integer> menuIds = roleFunmenuDao.doFindFunmenuByRoleId(roleId);
		if(menuIds==null||menuIds.isEmpty())
			throw new AuthorizationException();
		//4.基于菜单id获取权限标识
		List<String> permissionsList = funmenuDao.findPermissions(menuIds.toArray(array));
		if(permissionsList==null||permissionsList.isEmpty())
			throw new AuthorizationException("66666");
		//5.封装数据并返回
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> permisssionSet=new HashSet<>();
		for(String permisssion:permissionsList) {
			 if(!StringUtils.isEmpty(permisssion)) {
				 permisssionSet.add(permisssion);
			 } 
		}
		System.out.println(permisssionSet);
		info.setStringPermissions(permisssionSet);
		return info;
	}

}








