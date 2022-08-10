package com.jack.service;

import com.jack.base.BaseService;
import com.jack.entity.Community;

import java.util.List;

public interface CommunityService extends BaseService<Community> {
    List<Community> findAll();
}
