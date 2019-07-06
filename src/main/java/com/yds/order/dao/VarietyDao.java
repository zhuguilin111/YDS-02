package com.yds.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yds.order.entity.Variety;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VarietyDao {

	List<Variety> findVarietyObjects();

}
