package com.yds;
/**Mybatis Sql业务功能测试类*/
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.yds.order.dao.MenuDao;
import com.yds.order.entity.Menu;

@RunWith(SpringRunner.class)
@SpringBootTest //执行spring boot 初始化
public class MybatisTests {
	//private static final Logger logger = LoggerFactory.getLogger(SysLogDao.class);//阿里巴巴标准的日志输出
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private MenuDao menuDao;
    
	@Test
	public void testSqlSessionFactory() {
		System.out.println(sqlSessionFactory);  
		//logger.info("ssf="+ssf);
	}
	
	/**Menu模块的sql业务功能测试*/
	@Test
	public void testFindMenuObjects() {
		List<Menu> lists = menuDao.findObjects(null);
		for (Menu menu : lists) {
           System.out.println(menu);
           System.out.println("来了");
		}
	}
}
