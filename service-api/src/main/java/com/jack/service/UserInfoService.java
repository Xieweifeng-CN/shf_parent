package com.jack.service;

import com.jack.base.BaseService;
import com.jack.entity.UserInfo;

public interface UserInfoService extends BaseService<UserInfo> {
    UserInfo getByPhone(String phone);
}
