package com.jack.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jack.base.BaseDao;
import com.jack.base.BaseServiceImpl;
import com.jack.dao.AdminDao;
import com.jack.dao.AdminRoleDao;
import com.jack.dao.RoleDao;
import com.jack.entity.AdminRole;
import com.jack.entity.Role;
import com.jack.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/5
 * @Description :
 **/
@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Override
    protected BaseDao<Role> getEntityDao() {
        return roleDao;
    }

    /**
     * 查询(用户-角色)关联情况
     */
    @Override
    public Map<String, Object> findRoleByAdminId(Long adminId) {
        //查询所有的角色
        List<Role> roles = roleDao.findAll();

        //根据用户id查询已经赋予的角色
        List<Long> existRoleIdList  = adminRoleDao.findRoleIdByAdminId(adminId);

        //对角色进行分类
        List<Role> noAssginRoleList = new ArrayList<>();
        List<Role> assginRoleList = new ArrayList<>();
        for (Role role : roles) {
            //已分配
            if (existRoleIdList.contains(role.getId())){
                //已经被选择
                assginRoleList.add(role);
            }else {
                //未被选择
                noAssginRoleList.add(role);
            }
        }
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("noAssginRoleList", noAssginRoleList);
        roleMap.put("assginRoleList", assginRoleList);
        return roleMap;
    }

    /**
     * 保存(用户-角色)关联
     */
    @Override
    public void saveUserRoleRealtionShip(Long adminId, Long[] roleIds) {
        // 重置关联
        adminRoleDao.deleteByAdminId(adminId);

        // 遍历所传角色
        for(Long roleId : roleIds) {
            // 判空
            if(StringUtils.isEmpty(roleId)){continue;}
            // 构建关联
            AdminRole userRole = new AdminRole();
            userRole.setAdminId(adminId);
            userRole.setRoleId(roleId);
            adminRoleDao.insert(userRole);
        }
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
