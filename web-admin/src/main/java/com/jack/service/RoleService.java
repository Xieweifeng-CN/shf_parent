package com.jack.service;

import com.github.pagehelper.PageInfo;
import com.jack.base.BaseService;
import com.jack.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/5
 * @Description :
 **/
public interface RoleService extends BaseService<Role> {

    List<Role> findAll();

}
