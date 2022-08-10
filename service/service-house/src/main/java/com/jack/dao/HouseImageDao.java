package com.jack.dao;

import com.jack.base.BaseDao;
import com.jack.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseImageDao extends BaseDao<HouseImage> {

    List<HouseImage> findHouseImagesByHouseIdAndType(@Param("houseId") Long houseId, @Param("type") Integer type);
}
