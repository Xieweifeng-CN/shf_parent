package com.jack.dao;

import com.jack.base.BaseDao;
import com.jack.entity.Community;

import java.util.List;

public interface CommunityDao extends BaseDao<Community> {
    List<Community> findAll();
}
