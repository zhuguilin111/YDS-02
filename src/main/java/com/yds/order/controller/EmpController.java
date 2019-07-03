package com.yds.order.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yds.common.vo.EmpNode;
import com.yds.common.vo.JsonResult;
import com.yds.common.vo.PageObject;
import com.yds.order.entity.YdsEmp;
import com.yds.order.service.EmpService;


@Controller
@RequestMapping("/emp/")
public class EmpController {
	
	private UsernamePasswordToken token;
	@Autowired
	private EmpService empService;
	//向前端显示 menu_list
	@RequestMapping("doEmpListUI")
	public String doMenuListUI() {
		return "yds/emp_list";
	}
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String username,Integer pageCurrent){
		PageObject<EmpNode> pageObject=
				empService.findPageObjects(username,
						pageCurrent);
		return new JsonResult(pageObject);
	}
	@RequestMapping("doEmpEditUI")
	public String doEmpEditUI() {
		return "yds/emp_edit";
		
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		System.out.println(id);
		Map<String, Object> map = empService.doFindObjectById(id);
		System.out.println(map);
		return new JsonResult(map);
		
	}
	//保存添加的数据到数据库
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(YdsEmp entity,Integer[] roleIds) {
		String email = entity.getEmail();
		String mobile = entity.getMobile();
		if (email=="") {
			throw new IllegalArgumentException("您的邮箱不能为空");
		}
		if (!email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
			throw new IllegalArgumentException("请输入正确的邮箱格式！！");
		}
		if (mobile=="") {
			throw new IllegalArgumentException("您的手机号码号码不能为空！！");
		}
		if (!mobile.matches("1\\d{10}")) {
			throw new IllegalArgumentException("请输入正确的手机号码！");
		}
		
		int rows = empService.doSaveObject(entity, roleIds);
		return new JsonResult("添加了"+rows+"条记录");
		
	};
	//修改员工信息
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(YdsEmp entity,Integer[] roleIds){
		String email = entity.getEmail();
		String mobile = entity.getMobile();
		if (email=="") {
			throw new IllegalArgumentException("您的邮箱不能为空");
		}
		if (!email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
			throw new IllegalArgumentException("请输入正确的邮箱格式！！");
		}
		if (mobile=="") {
			throw new IllegalArgumentException("您的手机号码号码不能为空！！");
		}
		if (!mobile.matches("1\\d{10}")) {
			throw new IllegalArgumentException("请输入正确的手机号码！");
		}
		int row = empService.doUpdateObject(entity, roleIds);
		return new JsonResult("修改了"+row+"记录");
		
	}
	//启用禁用员工账号
	@ResponseBody
	@RequestMapping("doValidById")
	public JsonResult doValidById(
						Integer id,
						Integer valid,
						String modifiedUser){
		int row = empService.validById(id, valid, modifiedUser);
		return new JsonResult(row);
		
	}
	//登录认证
	@RequestMapping("doLogin")
	@ResponseBody
	   public JsonResult doLogin(String username,String password){
			//1.获取Subject对象
		   Subject subject=SecurityUtils.getSubject();
		   //2.通过Subject提交用户信息,交给shiro框架进行认证操作
		   //2.1对用户进行封装
		    token=
		   new UsernamePasswordToken(
				   username,//身份信息
				   password);//凭证信息
		   //2.2对用户信息进行身份认证
		   subject.login(token);
		   //分析:
		   //1)token会传给shiro的SecurityManager
		   //2)SecurityManager将token传递给认证管理器
		   //3)认证管理器会将token传递给realm
		   return new JsonResult("login ok");
	   }
	
	/** 向前端发送登录用户名*/
	@RequestMapping("getUserName")
	@ResponseBody
	public String getUserName() {
		YdsEmp emp = (YdsEmp)SecurityUtils.getSubject().getPrincipal();
		String username = emp.getUsername();
		return username;
	}
	/** 根据id删除员工*/
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		int rows = empService.doDeleteObject(id);
		return new JsonResult("删除了"+rows+"条记录");
		
	}
	/** AjaxCheckUsername*/
	@RequestMapping("AjaxCheckEmail")
	@ResponseBody
	public String AjaxCheckEmail(String email) {
		if (!email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
			return "请输入正确的邮箱格式!";
		}else {
			return "邮箱格式校验通过!";
		}	
	}
	
	/** AjaxCheckPhone*/
	@RequestMapping("AjaxCheckPhone")
	@ResponseBody
	public String AjaxCheckPhone(String mobile) {
		if (!mobile.matches("1\\d{10}")) {
			return "请输入正确的电话格式!";
		}else {
			return "电话号码格式校验通过!";
		}	
	}
}
