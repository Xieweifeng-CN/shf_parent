package com.jack.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jack.base.BaseController;
import com.jack.entity.Dict;
import com.jack.result.Result;
import com.jack.service.DictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/8
 * @Description :
 **/
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

    @Reference
    private DictService dictService;

    private final static String PAGE_INDEX = "dict/index";

    /**
     * 根据id获取子节点数据
     */
    @RequestMapping("/findListByParentId/{id}")
    @ResponseBody
    public Result<List<Dict>> findListByParentId(@PathVariable("id") Long parentId){
        List<Dict> dicts = dictService.findListByParentId(parentId);
        return Result.ok(dicts);
    }

    /**
     * 根据编码获取子节点数据列表
     */
    @GetMapping(value = "findListByDictCode/{dictCode}")
    @ResponseBody
    public Result<List<Dict>> findListByDictCode(@PathVariable String dictCode) {
        List<Dict> list = dictService.findListByDictCode(dictCode);
        return Result.ok(list);
    }

    /**
     * 生成树,根据id获取所有子节点
     */
    @GetMapping("/findZnodes")
    @ResponseBody
    public Result findZnodes(@RequestParam(value = "id",defaultValue = "0") Long id){
        System.out.println("请求成功");
        List<Map<String, Object>> znodes = dictService.findZnodes(id);
        return Result.ok(znodes);
    }

    @GetMapping
    public String index(){
        return PAGE_INDEX;
    }
}
