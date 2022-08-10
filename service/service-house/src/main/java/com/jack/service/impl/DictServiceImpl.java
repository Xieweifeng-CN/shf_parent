package com.jack.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jack.dao.DictDao;
import com.jack.entity.Dict;
import com.jack.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/8
 * @Description :
 **/
@Service(interfaceClass = DictService.class)
@Transactional
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public List<Map<String, Object>> findZnodes(Long id) {
        // 根据id获取子节点数据
        List<Dict> dictList = dictDao.findListByParentId(id);

        // 构建ztree,返回数据[{ id:2, isParent:true, name:"随意勾选 2"}]
        ArrayList<Map<String,Object>> ztree = new ArrayList<>();
        for (Dict dict : dictList){
            Map<String, Object> map = new HashMap<>();
            map.put("id",dict.getId());
            // 判断该节点是否是父节点
            Integer count = dictDao.isParentNode(dict.getId());
            map.put("isParent",count>0);
            map.put("name",dict.getName());
            ztree.add(map);
        }
        return ztree;
    }

    @Override
    public List<Dict> findListByParentId(Long parentId) {
        return dictDao.findListByParentId(parentId);
    }

    @Override
    public List<Dict> findListByDictCode(String dictCode) {
        Dict dict = dictDao.getByDictCode(dictCode);
        if (null == dict){return null;}
        return this.findListByParentId(dict.getId());
    }

    @Override
    public String getNameById(Long houseTypeId) {
        return dictDao.getNameById(houseTypeId);
    }
}
