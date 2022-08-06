package com.jack.service.impl;

import com.jack.base.BaseDao;
import com.jack.base.BaseServiceImpl;
import com.jack.dao.AdminDao;
import com.jack.entity.Admin;
import com.jack.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/5
 * @Description :
 **/
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    protected BaseDao<Admin> getEntityDao() {
        return adminDao;
    }
}
