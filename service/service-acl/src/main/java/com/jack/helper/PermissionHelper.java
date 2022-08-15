package com.jack.helper;

import com.jack.entity.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/15
 * @Description :
 **/
public class PermissionHelper {

    /**
     * 使用递归方法建菜单
     */
    public static List<Permission> build(List<Permission> treeNodes) {
        ArrayList<Permission> trees = new ArrayList<>();
        for (Permission treeNode : treeNodes) {
            // 查出1级节点
            if (treeNode.getParentId() == 0){
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     */
    public static Permission findChildren(Permission treeNode, List<Permission> treeNodes) {
        for (Permission it : treeNodes) {
            // 节点存在子节点
            if (treeNode.getId().equals(it.getParentId())) {
                // 赋予子节点级别
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    // 设置子节点集合
                    treeNode.setChildren(new ArrayList<Permission>());
                }
                // 添加子节点
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
