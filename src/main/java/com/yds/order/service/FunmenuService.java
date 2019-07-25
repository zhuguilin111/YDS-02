package com.yds.order.service;

import java.util.List;
import java.util.Map;

import com.yds.common.vo.Node;
import com.yds.order.entity.Funmenu;

public interface FunmenuService {
	List<Map<String, Object>> findObjects();

	int deleteObject(Integer id);

	List<Node> findZtreeMenuNodes();

	int saveObject(Funmenu funmenu);

	int updateObject(Funmenu gong);

	List<Funmenu> doFindZtreeMenuNodes();

    List<Funmenu> findFunmenusByRoleId(Integer[] id);
}
