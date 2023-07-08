package com.study.ssyx.product.service;

//import com.study.ssyx.product.entity.SkuPoster;
import com.atguigu.ssyx.model.product.SkuPoster;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
public interface SkuPosterService extends IService<SkuPoster> {

    List<SkuPoster> findBySkuId(long skuId);

}
