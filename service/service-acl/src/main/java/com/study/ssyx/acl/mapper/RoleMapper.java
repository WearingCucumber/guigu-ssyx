package com.study.ssyx.acl.mapper;

import com.atguigu.ssyx.model.acl.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
