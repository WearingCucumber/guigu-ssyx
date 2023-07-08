package com.study.ssyx.acl.service;

import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.model.user.User;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {
    IPage<Admin> selectAdminPage(AdminQueryVo adminQueryVo, Page<Admin> adminPage);
}
