package com.jack.dao;

import com.github.pagehelper.Page;
import com.jack.base.BaseDao;
import com.jack.entity.House;
import com.jack.vo.HouseQueryVo;
import com.jack.vo.HouseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseDao extends BaseDao<House> {
    @Override
    List<House> findAll();

    Page<HouseVo> findListPage(@Param("vo") HouseQueryVo houseQueryVo);
}
