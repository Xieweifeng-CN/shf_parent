package com.jack.dao;

import com.jack.base.BaseDao;
import com.jack.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends BaseDao<Role> {
    List<Role> findAll();
}
