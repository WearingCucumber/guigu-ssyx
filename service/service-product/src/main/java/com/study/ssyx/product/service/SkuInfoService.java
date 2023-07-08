package com.study.ssyx.product.service;

//import com.study.ssyx.product.entity.SkuInfo;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.vo.product.SkuInfoQueryVo;
import com.atguigu.ssyx.vo.product.SkuInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
public interface SkuInfoService extends IService<SkuInfo> {

    IPage<SkuInfo> getPageList(Page<SkuInfo> skuInfoPage, SkuInfoQueryVo skuInfoQueryVo);

    void saveSkuInfoVo(SkuInfoVo skuInfoVo);

    SkuInfoVo getSkuInfoVo(long id);

    void check(long skuId, Integer status);

    void publish(long id, Integer status);

    void isNewPerson(long id, Integer status);
}
