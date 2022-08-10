package com.jack.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jack.base.BaseController;
import com.jack.entity.Admin;
import com.jack.entity.HouseBroker;
import com.jack.service.AdminService;
import com.jack.service.HouseBrokerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/10
 * @Description :
 **/
@Controller
@RequestMapping("/houseBroker")
public class HouseBrokerController extends BaseController {
    @Reference
    private HouseBrokerService houseBrokerService;

    @Reference
    private AdminService adminService;

    private final static String LIST_ACTION = "redirect:/house/";
    private final static String PAGE_CREATE = "houseBroker/create";
    private final static String PAGE_EDIT = "houseBroker/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    /**
     * 进入新增
     */
    @GetMapping("/create")
    public String create(ModelMap model, @RequestParam("houseId") Long houseId) {
        List<Admin> adminList = adminService.findAll();
        model.addAttribute("adminList",adminList);
        model.addAttribute("houseId",houseId);
        return PAGE_CREATE;
    }

    /**
     * 修改跳转
     */
    @RequestMapping("/edit/{id}")
    public String getEditPage(@PathVariable("id") Long id , Map map){
        //调用HouseBrokerService中查询经纪人的方法
        HouseBroker houseBroker = houseBrokerService.getById(id);
        //将经纪人放到request域中
        map.put("houseBroker",houseBroker);
        //查询出所有的经纪人
        List<Admin> all = adminService.findAll();
        //将所有的经纪人放到request域中
        map.put("adminList",all);
        return "houseBroker/edit";
    }

    /**
     * 修改保存
     */
    @RequestMapping("/update")
    public String update(HouseBroker houseBroker){
        //根据HouseBroker对象中经纪人的id获取经纪人对象
        Admin admin = adminService.getById(houseBroker.getBrokerId());
        //将经纪人的名字，头像地址设置到houseBroker对象中
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        //调用HouseBrokerService中更新的方法
        houseBrokerService.update(houseBroker);
        //去common下的成功页面
        return "common/successPage";
    }

    //删除经纪人
    @RequestMapping("/delete/{houseId}/{brokerId}")
    public String delete(@PathVariable("houseId") Long houseId , @PathVariable("brokerId") Long brokerId){
        //调用HouseBroker中删除经纪人的方法
        houseBrokerService.delete(brokerId);
        //重定向到查询房源详情的方法
        return "redirect:/house/"+houseId;
    }
}
