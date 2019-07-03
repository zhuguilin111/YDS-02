package com.yds.order.service;

import com.yds.common.vo.PageObject;
import com.yds.order.entity.Table;

public interface TableService {
    @SuppressWarnings("rawtypes")
	PageObject findAll(Integer pageCurrent, Integer state);

    void saveObject(Table entity);

    void updateState(Integer id,Integer state);

    void deleteObjects(Integer[] ids);

	Table findObjectByTableId(Integer tableId);
}
