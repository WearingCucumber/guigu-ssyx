package com.study.ssyx.search.service.impl;

import com.atguigu.ssyx.enums.SkuType;
import com.atguigu.ssyx.model.product.Category;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.model.search.SkuEs;
import com.study.ssyx.client.product.ProductFeignClient;
import com.study.ssyx.search.repository.SkuRepository;
import com.study.ssyx.search.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public void upperSku(Long skuId) {
        //1.通过远程调用  根据skuid获取相关信息
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        if (skuInfo == null) return;
        Category category = productFeignClient.getCategory(skuInfo.getCategoryId());
        //2.过去数据封装SkuEs对象
        SkuEs skuEs = new SkuEs();
        if (category != null) {
            skuEs.setCategoryId(category.getId());
            skuEs.setCategoryName(category.getName());
        }
        if (skuInfo != null) {
            skuEs.setId(skuInfo.getId());
            skuEs.setKeyword(skuInfo.getSkuName() + "," + skuEs.getCategoryName());
            skuEs.setWareId(skuInfo.getWareId());
            skuEs.setIsNewPerson(skuInfo.getIsNewPerson());
            skuEs.setImgUrl(skuInfo.getImgUrl());
            skuEs.setTitle(skuInfo.getSkuName());
            if (skuInfo.getSkuType() == SkuType.COMMON.getCode()) {
                skuEs.setSkuType(0);
                skuEs.setPrice(skuInfo.getPrice().doubleValue());
                skuEs.setStock(skuInfo.getStock());
                skuEs.setSale(skuInfo.getSale());
                skuEs.setPerLimit(skuInfo.getPerLimit());
            }

        }
        //3.调用方法添加ES
        skuRepository.save(skuEs);
    }

    @Override
    public void lowerSku(Long skuId) {
        skuRepository.deleteById(skuId);

    }
}
