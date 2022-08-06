package com.jack.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jack.service.HelloService;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/6
 * @Description :
 **/
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
