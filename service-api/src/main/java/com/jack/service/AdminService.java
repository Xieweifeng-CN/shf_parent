package com.jack.service;

import com.jack.base.BaseService;
import com.jack.entity.Admin;

import java.util.List;

public interface AdminService extends BaseService<Admin> {
    List<Admin> findAll();
}
