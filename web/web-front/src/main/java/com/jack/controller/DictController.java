package com.jack.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jack.base.BaseController;
import com.jack.entity.Dict;
import com.jack.result.Result;
import com.jack.service.DictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/12
 * @Description :
 **/
@RestController
@RequestMapping("/dict")
@CrossOrigin
public class DictController extends BaseController {

    @Reference
    private DictService dictService;

    /**
     * 根据上级id获取子节点数据
     */
    @RequestMapping(value = "findListByParentId/{parentId}")
    public Result<List<Dict>> findListByParentId(@PathVariable Long parentId) {
        List<Dict> list = dictService.findListByParentId(parentId);
        return Result.ok(list);
    }

    /**
     * 根据编码获取子节点数据列表
     */
    @RequestMapping("/findListByDictCode/{dictCode}")
    public Result<List<Dict>> findListByDictCode(@PathVariable String dictCode){
        List<Dict> list = dictService.findListByDictCode(dictCode);

        return Result.ok(list);
    }
}
