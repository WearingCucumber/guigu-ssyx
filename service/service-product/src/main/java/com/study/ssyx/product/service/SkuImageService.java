package com.study.ssyx.product.service;

//import com.study.ssyx.product.entity.SkuImage;
import com.atguigu.ssyx.model.product.SkuImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品图片 服务类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
public interface SkuImageService extends IService<SkuImage> {

    List<SkuImage> findBySkuId(long skuId);
}
