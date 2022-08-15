package com.jack.service;

import com.github.pagehelper.PageInfo;
import com.jack.base.BaseService;
import com.jack.entity.House;
import com.jack.vo.HouseQueryVo;
import com.jack.vo.HouseVo;

import java.util.List;

public interface HouseService extends BaseService<House> {
    List<House> findAll();

    void publish(Long id, Integer status);

    PageInfo<HouseVo> findListPage(int pageNum, int pageSize, HouseQueryVo houseQueryVo);
}
