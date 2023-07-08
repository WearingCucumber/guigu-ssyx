package com.study.ssyx.acl.service.impl;

import com.atguigu.ssyx.model.acl.AdminRole;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.ssyx.acl.mapper.RoleMapper;
import com.study.ssyx.acl.service.AdminRoleService;
import com.study.ssyx.acl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private AdminRoleService adminRoleService;
    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        //获取条件值
        String roleName = roleQueryVo.getRoleName();
        //判断条件值是否为空 ，不为空则封装查询条件
        //创建mp条件对象
        LambdaQueryWrapper<Role> wrapper  = new LambdaQueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(roleName),Role::getRoleName,roleName);
        //调用方法实现条件分页查询
        IPage<Role> rolePage = baseMapper.selectPage(pageParam, wrapper);
        //返回分页对象
        return rolePage;
    }

    @Override
    public Map<String, Object> getRoleByAdminId(long adminId) {
        //查询所有角色
        List<Role> roles = baseMapper.selectList(null);
        //更具用户id查询用户分配角色列表
        LambdaQueryWrapper<AdminRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminRole::getAdminId,adminId);
        List<AdminRole> list = adminRoleService.list(queryWrapper);
        List<Long> idList =
                list.stream()
                .map(item -> item.getRoleId())
                .collect(Collectors.toList());
        ArrayList<Role> assignRoleList = new ArrayList<>();
        for (Role role : roles) {
            if (idList.contains(role.getId()))
                assignRoleList.add(role);
        }
        Map<String,Object> res = new HashMap<>();
        res.put("allRolesList",roles);
        res.put("assignRoles",assignRoleList);
        return res;
    }
//为用户进行分配
    @Override
    public void saveAdminRole(Long adminId, Long[] roleId) {
        //删除用户已经分配过的角色数据
        LambdaQueryWrapper<AdminRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminRole::getAdminId,adminId);
        adminRoleService.remove(queryWrapper);
        //更具用户id删除admin_role表里对应的数据
        //重新分配
        ArrayList<AdminRole> adminRoles = new ArrayList<>();
        for (Long roleid : roleId) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleid);
            adminRoles.add(adminRole);
        }
        adminRoleService.saveBatch(adminRoles);
    }
}
