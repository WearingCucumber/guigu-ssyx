package com.study.ssyx.product.service.impl;

//import com.study.ssyx.product.entity.SkuImage;
import com.atguigu.ssyx.model.product.SkuImage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.ssyx.product.mapper.SkuImageMapper;
import com.study.ssyx.product.service.SkuImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品图片 服务实现类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageMapper, SkuImage> implements SkuImageService {

    @Override
    public List<SkuImage> findBySkuId(long skuId) {
        LambdaQueryWrapper<SkuImage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SkuImage::getSkuId,skuId);
        return baseMapper.selectList(queryWrapper);
    }
}
