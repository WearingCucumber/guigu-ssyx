package com.study.ssyx.product.service;

//import com.study.ssyx.product.entity.SkuAttrValue;
import com.atguigu.ssyx.model.product.SkuAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * spu属性值 服务类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
public interface SkuAttrValueService extends IService<SkuAttrValue> {

    List<SkuAttrValue> findBySkuId(long skuId);
}
