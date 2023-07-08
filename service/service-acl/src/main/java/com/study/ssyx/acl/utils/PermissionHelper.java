package com.study.ssyx.acl.utils;

import com.atguigu.ssyx.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;

public class PermissionHelper {
    public static List<Permission> buildPermission(List<Permission> allList){
        List<Permission> trees = new ArrayList<>();
        //遍历所有菜单list集合
        for (Permission permission : allList) {
            //pid = 0 数据第一层
            if (permission.getPid() == 0) {
                permission.setLevel(1);
                //调用方法，从第一层下面开始找
                trees.add(findChildren(permission,allList));
            }
        }

        return  trees;
    }
    //递归往下去找 找子节点

    /**
     *
     * @param permission 上层节点，从这里望下去找
     * @param allList 所有菜单
     * @return
     */
    private static Permission findChildren(Permission permission, List<Permission> allList) {
        permission.setChildren(new ArrayList<Permission>());
        for (Permission perm : allList) {
            //当前节点id = pid是否一样
            if (permission.getId().longValue() == perm.getPid().longValue()){
                int level = permission.getLevel()+1;
                perm.setLevel(level);
                if (permission.getChildren() == null){
                    permission.setChildren(new ArrayList<>());
                }
                //封装下一层数据
                permission.getChildren().add(findChildren(perm,allList));
            }
        }
        return  permission;
    }
}
