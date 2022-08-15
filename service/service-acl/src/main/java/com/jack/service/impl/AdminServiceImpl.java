package com.jack.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jack.base.BaseDao;
import com.jack.base.BaseServiceImpl;
import com.jack.dao.AdminDao;
import com.jack.entity.Admin;
import com.jack.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/5
 * @Description :
 **/
@Service(interfaceClass = AdminService.class)
@Transactional
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    protected BaseDao<Admin> getEntityDao() {
        return adminDao;
    }

    @Override
    public Admin getByUsername(String username) {
        return adminDao.getByUsername(username);
    }
}
