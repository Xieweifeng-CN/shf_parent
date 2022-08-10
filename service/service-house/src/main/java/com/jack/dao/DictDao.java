package com.jack.dao;

import com.jack.entity.Dict;

import java.util.List;

public interface DictDao {

    String getNameById(Long id);

    Dict getByDictCode(String dictCode);

    /**
     * 根据父节点查询所有子节点
     */
    List<Dict> findListByParentId(Long parentId);

    /**
     * 判断该节点是否有子节点
     */
    Integer isParentNode(Long id);
}
