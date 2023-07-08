package com.study.ssyx.product.service.impl;

//import com.study.ssyx.product.entity.SkuPoster;
import com.atguigu.ssyx.model.product.SkuPoster;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.ssyx.product.mapper.SkuPosterMapper;
import com.study.ssyx.product.service.SkuPosterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务实现类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
@Service
public class SkuPosterServiceImpl extends ServiceImpl<SkuPosterMapper, SkuPoster> implements SkuPosterService {

    @Override
    public List<SkuPoster> findBySkuId(long skuId) {
        LambdaQueryWrapper<SkuPoster> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SkuPoster::getSkuId,skuId);
        return baseMapper.selectList(queryWrapper);
    }
}
