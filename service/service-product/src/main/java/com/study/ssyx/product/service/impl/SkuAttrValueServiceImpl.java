package com.study.ssyx.product.service.impl;

//import com.study.ssyx.product.entity.SkuAttrValue;
import com.atguigu.ssyx.model.product.SkuAttrValue;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.ssyx.product.mapper.SkuAttrValueMapper;
import com.study.ssyx.product.service.SkuAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * spu属性值 服务实现类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper, SkuAttrValue> implements SkuAttrValueService {

    @Override
    public List<SkuAttrValue> findBySkuId(long skuId) {
        LambdaQueryWrapper<SkuAttrValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SkuAttrValue::getSkuId,skuId);
        return baseMapper.selectList(queryWrapper);
    }
}
