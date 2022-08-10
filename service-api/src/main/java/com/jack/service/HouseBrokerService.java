package com.jack.service;

import com.jack.base.BaseService;
import com.jack.entity.HouseBroker;

import java.util.List;

public interface HouseBrokerService extends BaseService<HouseBroker> {
    List<HouseBroker> findListByHouseId(Long id);
}
