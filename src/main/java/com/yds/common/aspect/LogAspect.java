package com.yds.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yds.common.annotation.RequestLog;
import com.yds.order.dao.LogDao;
import com.yds.order.entity.Log;
import com.yds.order.entity.YdsEmp;

@Aspect
@Service
public class LogAspect {
	@Autowired
	private LogDao logDao;
	/**定义切入点*/
	@Pointcut("@annotation(com.yds.common.annotation.RequestLog)")
	public void logPointCut(){}
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable{//连接点
		long startTime=System.currentTimeMillis();
    	//执行目标方法(result为目标方法的执行结果)
    	Object result=jp.proceed();
    	long endTime=System.currentTimeMillis();
    	long time=endTime-startTime;
    	saveSysLog(jp,time);
		return result;

	}
	@SuppressWarnings("unused")
	private void saveSysLog(ProceedingJoinPoint jp, long time) throws JsonProcessingException, NoSuchMethodException {
		//1.获取用户行为信息
		MethodSignature ms = (MethodSignature)jp.getSignature();
    	Class<?> targetClass = jp.getTarget().getClass();
    	//获取类名
    	String className = targetClass.getName();
    	//获取接口声明的方法名
    	String methodName = ms.getMethod().getName();
    	//获取接口声明的方法参数类型
    	Class<?>[] parameterTypes=ms.getMethod().getParameterTypes();
    	//获取目标对象方法
    	Method targetMethod = targetClass.getDeclaredMethod(methodName,parameterTypes);
     	//判定目标方法上是否有RequestLog注解
    	boolean flag = targetMethod.isAnnotationPresent(RequestLog.class);
    	//获取方法参数
    	Object[] paramsObj=jp.getArgs();
    	//将参数转换为字符串
    	String params=new ObjectMapper().writeValueAsString(paramsObj);
    	System.out.println("params="+params);
		YdsEmp e = (YdsEmp)SecurityUtils.getSubject().getPrincipal(); 
    	
		 //2.封装用户行为信息
		 Log log=new Log();
		 log.setIp("192.168.0.3");
		 log.setUsername(e.getUsername());
		 log.setMethod(methodName);//类全名+方法
		 log.setParams(params);//方法实际参数值
	    	if(flag){
	        	RequestLog requestLog = targetMethod.getDeclaredAnnotation(RequestLog.class);
	        	log.setOperation(requestLog.value());
	        	}//添加,修改,删除,..
		 log.setTime(time);
		 log.setCreatedTime(new Date());
		 //3.将用户行为信息存储到数据库
		 logDao.insertObject(log);
	 }
	@SuppressWarnings("unused")
	private String getOperation(Class<?> targetCls, MethodSignature ms) throws NoSuchMethodException {
		String operation="operation";
		 //Method method=ms.getMethod();//接口方法
		 Method  targetMethod=targetCls.getDeclaredMethod(
				 ms.getName(),
				 ms.getParameterTypes());
		 RequestLog rLog=
		 targetMethod.getDeclaredAnnotation(RequestLog.class);
		 if(rLog!=null) {
			 operation=rLog.value();
		 }
		return operation;
	}
	@SuppressWarnings("unused")
	private String getRequestParams(ProceedingJoinPoint jp) throws JsonProcessingException {
		Object[] args=jp.getArgs();
		 String params="[]";
		 if(args.length>0) {
		 params=new ObjectMapper()
		 .writeValueAsString(args);
		 }
		return params;
	}

}
