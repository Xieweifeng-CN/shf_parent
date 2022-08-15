package com.jack.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jack.entity.Admin;
import com.jack.entity.Permission;
import com.jack.service.AdminService;
import com.jack.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/5
 * @Description :
 **/
@Controller
public class IndexController {

    @Reference
    private AdminService adminService;

    @Reference
    private PermissionService permissionService;

    private final static String PAGE_INDEX = "frame/index";
    private final static String PAGE_MAIN = "frame/main";
    private final static String PAGE_AUTH = "frame/auth";

    /**
     * 框架首页
     */
    @GetMapping("/")
    public String index(ModelMap model) {
        //获取授权用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        //根据授权获取具体用户信息
        Admin admin = adminService.getByUsername(user.getUsername());
        List<Permission> permissionList = permissionService.findMenuPermissionByAdminId(admin.getId());
        model.addAttribute("admin", admin);
        model.addAttribute("permissionList",permissionList);
        return PAGE_INDEX;
    }

    /**
     * 框架主页
     */
    @GetMapping("/main")
    public String main() {
        return PAGE_MAIN;
    }

    /**
     * 授权登录页面
     */
    @RequestMapping("/login")
    public String goLoginPage(){
        return "frame/login";
    }

    /**
     * 授权提示
     */
    @GetMapping("/auth")
    public String auth() {
        return PAGE_AUTH;
    }
}
