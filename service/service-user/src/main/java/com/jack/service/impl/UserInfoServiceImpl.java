package com.jack.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jack.base.BaseDao;
import com.jack.base.BaseServiceImpl;
import com.jack.dao.UserInfoDao;
import com.jack.entity.UserInfo;
import com.jack.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author :Jack
 * @CreatTime : 2022/8/13
 * @Description :
 **/
@Service(interfaceClass = UserInfoService.class)
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired(required = false)
    private UserInfoDao userInfoDao;

    @Override
    protected BaseDao<UserInfo> getEntityDao() {
        return userInfoDao;
    }

    @Override
    public UserInfo getByPhone(String phone) {
        return userInfoDao.getByPhone(phone);
    }
}
