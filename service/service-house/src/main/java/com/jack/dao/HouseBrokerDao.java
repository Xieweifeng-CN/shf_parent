package com.jack.dao;

import com.jack.base.BaseDao;
import com.jack.entity.HouseBroker;

import java.util.List;

public interface HouseBrokerDao extends BaseDao<HouseBroker> {

    List<HouseBroker> findListByHouseId(Long id);
}
