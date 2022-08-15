package com.jack.dao;

import com.jack.base.BaseDao;
import com.jack.entity.AdminRole;

import java.util.List;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/14
 * @Description :
 **/
public interface AdminRoleDao extends BaseDao<AdminRole> {

    void deleteByAdminId(Long adminId);

    List<Long> findRoleIdByAdminId(Long adminId);
}
