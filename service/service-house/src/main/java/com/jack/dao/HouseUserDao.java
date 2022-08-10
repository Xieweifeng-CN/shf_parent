package com.jack.dao;

import com.jack.base.BaseDao;
import com.jack.entity.HouseUser;

import java.util.List;

public interface HouseUserDao extends BaseDao<HouseUser> {

    List<HouseUser> findHouseUsersByHouseId(Long houseId);
}
