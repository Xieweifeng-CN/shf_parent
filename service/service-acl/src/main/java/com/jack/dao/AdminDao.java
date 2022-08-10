package com.jack.dao;

import com.jack.base.BaseDao;
import com.jack.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao extends BaseDao<Admin> {

    List<Admin> findAll();
}
