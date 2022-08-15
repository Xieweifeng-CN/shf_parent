package com.jack.service;

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

    /**
     * 根据用户获取角色数据
     */
    Map<String, Object> findRoleByAdminId(Long adminId);

    /**
     * 分配角色
     */
    void saveUserRoleRealtionShip(Long adminId, Long[] roleIds);

    List<Role> findAll();

}
