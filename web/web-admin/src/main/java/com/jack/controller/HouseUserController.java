package com.jack.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jack.base.BaseController;
import com.jack.entity.HouseUser;
import com.jack.service.HouseUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/10
 * @Description :
 **/
@Controller
@RequestMapping("/houseUser")
public class HouseUserController extends BaseController {

    @Reference
    private HouseUserService houseUserService;

    private final static String LIST_ACTION = "redirect:/house/";

    private final static String PAGE_CREATE = "houseUser/create";
    private final static String PAGE_EDIT = "houseUser/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    /**
     * 新增跳转
     */
    @GetMapping("/create")
    public String create(ModelMap model, @RequestParam("houseId") Long houseId) {
        model.addAttribute("houseId",houseId);
        return PAGE_CREATE;
    }

    /**
     * 新增保存
     */
    @PostMapping("/save")
    public String save(HouseUser houseUser){
        houseUserService.insert(houseUser);
        return PAGE_SUCCESS;
    }

    /**
     * 编辑跳转
     */
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id){
        HouseUser houseUser = houseUserService.getById(id);
        model.addAttribute("houseUser",houseUser);
        return PAGE_EDIT;
    }

    /**
     * 编辑修改保存
     */
    @PostMapping("/update")
    public String update(HouseUser houseUser){
        houseUserService.update(houseUser);
        return PAGE_SUCCESS;
    }

    /**
     * 删除
     */
    @GetMapping("/delete/{houseId}/{id}")
    public String delete(ModelMap model,@PathVariable Long houseId,@PathVariable Long id){
        houseUserService.delete(id);
        return LIST_ACTION + houseId;
    }
}
