package com.study.ssyx.product.service.impl;

//import com.study.ssyx.product.entity.Attr;
import com.atguigu.ssyx.model.product.Attr;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.ssyx.product.mapper.AttrMapper;
import com.study.ssyx.product.service.AttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements AttrService {

    @Override
    public List<Attr> getList(Long groupId) {
        LambdaQueryWrapper<Attr> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Attr::getAttrGroupId,groupId);
        return this.list(queryWrapper);
    }
}
