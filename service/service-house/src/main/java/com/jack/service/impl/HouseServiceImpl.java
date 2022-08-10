package com.jack.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jack.base.BaseDao;
import com.jack.base.BaseServiceImpl;
import com.jack.dao.DictDao;
import com.jack.dao.HouseDao;
import com.jack.entity.House;
import com.jack.service.DictService;
import com.jack.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/9
 * @Description :
 **/
@Service(interfaceClass = HouseService.class)
@Transactional
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {


    @Autowired
    private HouseDao houseDao;

    @Autowired
    private DictService dictService;

    @Override
    protected BaseDao<House> getEntityDao() {
        return houseDao;
    }

    @Override
    public List<House> findAll() {
        return houseDao.findAll();
    }

    @Override
    public void publish(Long id, Integer status) {
        House house = new House();
        house.setId(id);
        house.setStatus(status);
        houseDao.update(house);
    }

    @Override
    public House getById(Serializable id) {
        House house = houseDao.getById(id);
        if(null == house) {return null;}

        //户型：
        String houseTypeName = dictService.getNameById(house.getHouseTypeId());
        //楼层
        String floorName = dictService.getNameById(house.getFloorId());
        //建筑结构：
        String buildStructureName = dictService.getNameById(house.getBuildStructureId());
        //朝向：
        String directionName = dictService.getNameById(house.getDirectionId());
        //装修情况：
        String decorationName = dictService.getNameById(house.getDecorationId());
        //房屋用途：
        String houseUseName = dictService.getNameById(house.getHouseUseId());
        house.setHouseTypeName(houseTypeName);
        house.setFloorName(floorName);
        house.setBuildStructureName(buildStructureName);
        house.setDirectionName(directionName);
        house.setDecorationName(decorationName);
        house.setHouseUseName(houseUseName);
        return house;
    }
}
