package com.jack.service;

import com.jack.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * 字典业务接口
 */
public interface DictService {
        /**
         * 查找所有节点
         */
        List<Map<String,Object>> findZnodes(Long id);

        /**
         * 根据上级id获取子节点
         */
        List<Dict> findListByParentId(Long parentId);

        /**
         * 根据编码获取子节点数据
         */
        List<Dict> findListByDictCode(String dictCode);


        /**
         * 根据id获取名字
         */
        String getNameById(Long houseTypeId);
}
