package com.jack.service;

import com.jack.base.BaseService;
import com.jack.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService extends BaseService<Permission> {
    /**
     *  获取用户功能权限
     */
    List<String> findCodeListByAdminId(Long adminId);

    /**
     * 菜单全部数据
     */
    List<Permission> findAllMenu();

    /**
     * 获取用户菜单权限
     */
    List<Permission> findMenuPermissionByAdminId(Long adminId);

    /**
     * 根据角色id获取授权权限数据
     */
    List<Map<String,Object>> findPermissionByRoleId(Long roleId);

    /**
     * 保存角色权限
     */
    void saveRolePermissionRealtionShip(Long roleId, Long[] permissionIds);
}
