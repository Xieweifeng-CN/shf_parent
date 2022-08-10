package com.jack.dao;

import com.jack.base.BaseDao;
import com.jack.entity.House;

import java.util.List;

public interface HouseDao extends BaseDao<House> {
    List<House> findAll();
}
