package com.jack.service;

import com.jack.base.BaseService;
import com.jack.entity.HouseUser;

import java.util.List;

public interface HouseUserService extends BaseService<HouseUser> {
    List<HouseUser> findListByHouseId(Long houseId);
}
