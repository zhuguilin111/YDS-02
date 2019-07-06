package com.yds.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yds.common.vo.Node;
import com.yds.order.entity.Funmenu;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface FunmenuDao {
	
	public List<Funmenu> findZTree();
	//查询功能菜单表中的授权标识
	List<String> findPermissions(Integer[] menuIds);
	
	List<Map<String,Object>> findObjects();
	/**查询又没有子菜单*/
	int getChildCount(@Param("id")Integer id);
	int deleteObject(@Param("id")Integer id);
	/**基于请求获取数据库对应的菜单表中的所有菜单信息*/
	List<Node> findZtreeGongNodes();
	/**添加菜单*/
	int insertObject(Funmenu funmenu);
	int updateObject(Funmenu funmenu);
}
