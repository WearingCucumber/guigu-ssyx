package com.study.ssyx.search.service;

import org.springframework.stereotype.Service;

@Service
public interface SkuService {
    void upperSku(Long skuId);

    void lowerSku(Long skuId);
}
