package com.jack.dao;

import com.jack.base.BaseDao;
import com.jack.entity.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo> {
    UserInfo getByPhone(String phone);
}
