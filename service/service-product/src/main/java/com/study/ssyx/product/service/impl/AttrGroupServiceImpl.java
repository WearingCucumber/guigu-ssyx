package com.study.ssyx.product.service.impl;

//import com.study.ssyx.product.entity.AttrGroup;
import com.atguigu.ssyx.model.product.Attr;
import com.atguigu.ssyx.model.product.AttrGroup;
import com.atguigu.ssyx.vo.product.AttrGroupQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.ssyx.product.mapper.AttrGroupMapper;
import com.study.ssyx.product.service.AttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 属性分组 服务实现类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup> implements AttrGroupService {

    @Override
    public IPage<AttrGroup> getPageList(Page<AttrGroup> attrGroupPage, AttrGroupQueryVo attrGroupQueryVo) {
        String name = attrGroupQueryVo.getName();
        LambdaQueryWrapper<AttrGroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(name),AttrGroup::getName,name);
        queryWrapper.orderByAsc(AttrGroup::getSort);
        IPage<AttrGroup> page = baseMapper.selectPage(attrGroupPage, queryWrapper);
        return page;
    }

    @Override
    public List<AttrGroup> findAllList() {
        LambdaQueryWrapper<AttrGroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(AttrGroup::getSort);
        return this.list(queryWrapper);
    }
}
