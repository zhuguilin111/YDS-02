package com.yds.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @Configuration 注解描述的类为一个配置对象, 此对象也会交给spring管理
 */
@Configuration // bean
public class SpringShiroConfig {// spring-shiro.xml
	/**
	 * @Bean 描述的方法,其返回值会交给spring管理
	 * @Bean 一般应用在整合第三bean资源时
	 */
	@Bean
	public SecurityManager newSecurityManager(@Autowired Realm realm) {
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		return sManager;
	}

	/**
	 * 配置此对象的目的是创建多个filter对象
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean("shiroFilterFactory")
	public ShiroFilterFactoryBean newShiroFilterFactoryBean(@Autowired SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);
		// 假如没有认证请求先访问此认证的url
		sfBean.setLoginUrl("/doLoginUI");
		// 定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		// 静态资源允许匿名访问:"anon"
		map.put("/assets/**", "anon");
		map.put("/bower_components/**", "anon");
		map.put("/build/**", "anon");
		map.put("/dist/**", "anon");
		map.put("/plugins/**", "anon");
		map.put("/layui/**", "anon");
		map.put("/doOrderUI", "anon");
		map.put("/alipay/notifyUI", "anon");
		map.put("/alipay/return", "anon");
		map.put("/alipay/pay", "anon");
		map.put("/doCheckTable", "anon");
		map.put("/menu/doFindObjects", "anon");
		map.put("/variety/doGetVarietyObjects", "anon");
		map.put("/order/doInsertObject", "anon");
		map.put("/emp/doLogin", "anon");

		map.put("/doLogout", "logout");// 自动查LoginUrl
		// 除了匿名访问的资源,其它都要认证("authc")后访问
		map.put("/**", "authc");
		sfBean.setFilterChainDefinitionMap(map);
		return sfBean;
	}

	// ==================================
	// 授权配置
	// 1.配置shiro中bean对象的生命周期管理
	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor newLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	// 2.配置代理创建器对象(此对象负责为所有advisor类型的对象创建代理对象,底层AOP)
	@DependsOn("lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daapc = new DefaultAdvisorAutoProxyCreator();
		daapc.setProxyTargetClass(true);
		return daapc;
	}

	/**
	 * 配置advisor对象,此对象中的方法要检测你要执行的业务方法上 是否有@RequiresPermissions注解,有此注解系统底层会调用
	 * ProxyCreator对象为方法所在类创建代理对象,然后通过代理 对象实现权限控制(AOP)
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor newAuthorizationAttributeSourceAdvisor(
			@Autowired SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		return advisor;
	}

}
