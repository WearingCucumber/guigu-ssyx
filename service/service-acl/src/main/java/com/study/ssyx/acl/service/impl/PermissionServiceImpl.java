package com.study.ssyx.acl.service.impl;

import com.atguigu.ssyx.model.acl.Permission;
import com.atguigu.ssyx.model.acl.RolePermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.ssyx.acl.mapper.PermissionMapper;
import com.study.ssyx.acl.mapper.RolePermissionMapper;
import com.study.ssyx.acl.service.PermissionService;
import com.study.ssyx.acl.service.RolePermissionService;
import com.study.ssyx.acl.utils.PermissionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private  RolePermissionService rolePermissionService;
    //查询所有菜单
    @Override
    public List<Permission> queryAllPermission() {
        //1.查询出所有菜单
        List<Permission> allPermissionList = baseMapper.selectList(null);
        //2.转换成要求的格式
        List<Permission> res = PermissionHelper.buildPermission(allPermissionList);
        return res;
    }
    //递归删除菜单
    @Override
    public void removeChildById(Long id) {
        List<Long> idList = new ArrayList<>();
        //根据当前菜单id获取所有子菜单的id
        //如果子菜单下面还有子菜单都要获取到
        //使用递归
        this.getAllPermissionId(id,idList);
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
    }

    @Override
    public List<Permission> getPermissionByAdminId(Long roleId) {
        //查询所有菜单信息
        List<Permission> allPermission = baseMapper.selectList(null);
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId,roleId);
        //通过角色id 查询 已经分配的权限
        List<RolePermission> listByRoleId = rolePermissionService.list(queryWrapper);
        //获取已经分配的权限的id
        List<Long> permissionIds = listByRoleId.stream().map(item -> item.getPermissionId()).collect(Collectors.toList());
        for (Permission permission : allPermission) {
            //遍历所有权限  如果是已经分配的  则将Select属性设置为true
            if (permissionIds.contains(permission.getId())){
                permission.setSelect(true);
            }
        }
        List<Permission> tree = PermissionHelper.buildPermission(allPermission);
        return tree;
    }

    @Override
    public void saveRolePermission(Long roleId, Long[] permissionId) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId,roleId);
        rolePermissionService.remove(queryWrapper);
        List<RolePermission> list = new ArrayList<>();
        for (Long id : permissionId) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(id);
            rolePermission.setRoleId(roleId);
            list.add(rolePermission);
        }
        rolePermissionService.saveBatch(list);
    }

    /**
     *
     * @param id 当前菜单id
     * @param idList 最终封装到list集合中 包含所有菜单id
     */
    private void getAllPermissionId(Long id, List<Long> idList) {
        //根据当前菜单id查询所有子菜单
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permission::getPid,id);
        List<Permission> childList = baseMapper.selectList(queryWrapper);
        //递归查询是否还有子菜单
        childList.stream().forEach(item->{
            //封装菜单id到idList中
            idList.add(item.getId());
            this.getAllPermissionId(item.getId(),idList);
        });
    }
}
