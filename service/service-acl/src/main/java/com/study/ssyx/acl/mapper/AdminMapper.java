package com.study.ssyx.acl.mapper;

import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.model.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
}
