package com.study.ssyx.acl.service.impl;

import com.atguigu.ssyx.model.acl.RolePermission;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.ssyx.acl.mapper.RolePermissionMapper;
import com.study.ssyx.acl.service.RolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}
