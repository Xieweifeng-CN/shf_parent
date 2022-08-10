package com.jack.service;

import com.jack.base.BaseService;
import com.jack.entity.House;

import java.util.List;

public interface HouseService extends BaseService<House> {
    List<House> findAll();

    void publish(Long id, Integer status);
}
