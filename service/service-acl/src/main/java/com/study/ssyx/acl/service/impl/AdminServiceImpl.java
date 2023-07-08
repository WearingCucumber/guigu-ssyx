package com.study.ssyx.acl.service.impl;

import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.model.user.User;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.ssyx.acl.mapper.AdminMapper;
import com.study.ssyx.acl.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public IPage<Admin> selectAdminPage(AdminQueryVo adminQueryVo, Page<Admin> adminPage) {
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        String username = adminQueryVo.getName();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(username),Admin::getUsername,username);
        IPage<Admin> adminPageModel = baseMapper.selectPage(adminPage, lambdaQueryWrapper);
        return adminPageModel;
    }
}
