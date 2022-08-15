package com.jack.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jack.base.BaseDao;
import com.jack.base.BaseServiceImpl;
import com.jack.dao.PermissionDao;
import com.jack.dao.RolePermissionDao;
import com.jack.entity.Permission;
import com.jack.entity.RolePermission;
import com.jack.helper.PermissionHelper;
import com.jack.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/14
 * @Description :
 **/
@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;
    
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    protected BaseDao<Permission> getEntityDao() {
        return permissionDao;
    }

    @Override
    public List<String> findCodeListByAdminId(Long adminId) {
        //超级管理员admin账号id为：1 //证明是系统管理员，查询所有权限
        if(adminId.longValue() == 1) {
            return permissionDao.findAllCodeList();
        }
        //根据管理员的id查询操作权限
        return permissionDao.findCodeListByAdminId(adminId);
    }

    @Override
    public List<Permission> findAllMenu() {
        //全部权限列表
        List<Permission> permissionList = permissionDao.findAll();
        if(CollectionUtils.isEmpty(permissionList)){ return null;}
        //构建树形数据,总共三级
        //把权限数据构建成树形结构数据
        return PermissionHelper.build(permissionList);
    }

    @Override
    public List<Permission> findMenuPermissionByAdminId(Long adminId) {
        List<Permission> permissionList = null;
        //admin账号id为 1
        if(adminId == 1){
            //如果是超级管理员,获取所有菜单
            permissionList = permissionDao.findAll();
        }else {
            permissionList = permissionDao.findListByAdminId(adminId);
        }
        //把权限数据构建成树型结构数据
        return PermissionHelper.build(permissionList);
    }

    @Override
    public List<Map<String, Object>> findPermissionByRoleId(Long roleId) {
        //全部权限列表
        List<Permission> pepermissionList  = permissionDao.findAll();

        //获取角色分配的权限
        List<Long> permissionIdList  = rolePermissionDao.findPermissionIdListByRoleId(roleId);

        //构建ztree数据
        // { id:2, pId:0, name:"随意勾选 2", checked:true, open:true}
        ArrayList<Map<String,Object>> zNodes = new ArrayList<>();
        for (Permission permission : pepermissionList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", permission.getId());
            map.put("pId", permission.getParentId());
            map.put("name", permission.getName());
            if(permissionIdList.contains(permission.getId())) {
                map.put("checked", true);
            }
            zNodes.add(map);
        }
        return zNodes;
    }

    @Override
    public void saveRolePermissionRealtionShip(Long roleId, Long[] permissionIds) {
        // 重置权限
        rolePermissionDao.deleteByRoleId(roleId);

        // 遍历所处权限
        for(Long permissionId : permissionIds) {
            // 判空
            if(StringUtils.isEmpty(permissionId)) {continue;}
            // 构建角色-权限关联
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            // 保存
            rolePermissionDao.insert(rolePermission);
        }
    }
}
