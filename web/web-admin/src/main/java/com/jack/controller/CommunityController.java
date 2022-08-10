package com.jack.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.jack.base.BaseController;
import com.jack.entity.Community;
import com.jack.entity.Dict;
import com.jack.service.CommunityService;
import com.jack.service.DictService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/8
 * @Description :
 **/
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    @Reference
    private CommunityService communityService;

    @Reference
    private DictService dictService;

    private final static String LIST_ACTION = "redirect:/community";

    private final static String PAGE_INDEX = "community/index";
    private final static String PAGE_SHOW = "community/show";
    private final static String PAGE_CREATE = "community/create";
    private final static String PAGE_EDIT = "community/edit";
    private final static String PAGE_SUCCESS = "common/successPage";

    /**
     * 初始化分页列表
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request){
        Map<String, Object> filters = getFilters(request);
        //防止页面报错，兼容处理
        if(!filters.containsKey("areaId")){
            filters.put("areaId","");
        }
        if(!filters.containsKey("plateId")) {
            filters.put("plateId", "");
        }
        PageInfo<Community> page = communityService.findPage(filters);
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("areaList",areaList);
        model.addAttribute("page",page);
        model.addAttribute("filters",filters);
        return PAGE_INDEX;
    }

    /**
     * 新增页面跳转
     */
    @GetMapping("/create")
    public String create(ModelMap model){
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("areaList",areaList);
        return PAGE_CREATE;
    }

    /**
     * 新增保存
     */
    @PostMapping("/save")
    public String save(Community community){
        communityService.insert(community);
        return PAGE_SUCCESS;
    }

    /**
     * 编辑页跳转
     */
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model,@PathVariable Long id){
        Community community = communityService.getById(id);
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        model.addAttribute("community",community);
        model.addAttribute("areaList",areaList);
        return PAGE_EDIT;
    }

    /**
     * 编辑修改保存
     */
    @PostMapping("/update")
    public String update(Community community){
        communityService.update(community);
        return PAGE_SUCCESS;
    }

    /**
     * 删除
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        communityService.delete(id);
        return LIST_ACTION;
    }
}
