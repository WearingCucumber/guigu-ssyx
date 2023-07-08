package com.study.ssyx.product.service;

//import com.study.ssyx.product.entity.Attr;
import com.atguigu.ssyx.model.product.Attr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
public interface AttrService extends IService<Attr> {

    List<Attr> getList(Long groupId);
}
