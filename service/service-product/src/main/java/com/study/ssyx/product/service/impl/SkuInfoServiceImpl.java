package com.study.ssyx.product.service.impl;

//import com.study.ssyx.product.entity.SkuInfo;
import com.atguigu.ssyx.model.product.SkuAttrValue;
import com.atguigu.ssyx.model.product.SkuImage;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.model.product.SkuPoster;
import com.atguigu.ssyx.vo.product.SkuInfoQueryVo;
import com.atguigu.ssyx.vo.product.SkuInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.ssyx.mq.constant.MqConst;
import com.study.ssyx.mq.service.RabbitService;
import com.study.ssyx.product.mapper.SkuInfoMapper;
import com.study.ssyx.product.service.SkuAttrValueService;
import com.study.ssyx.product.service.SkuImageService;
import com.study.ssyx.product.service.SkuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.ssyx.product.service.SkuPosterService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * sku信息 服务实现类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {
    @Autowired
    private SkuAttrValueService skuAttrValueService;
    @Autowired
    private SkuImageService skuImageService;
    @Autowired
    private SkuPosterService skuPosterService;
    @Autowired
    private RabbitService rabbitService;

    @Override
    public IPage<SkuInfo> getPageList(Page<SkuInfo> skuInfoPage, SkuInfoQueryVo skuInfoQueryVo) {
        LambdaQueryWrapper<SkuInfo> queryWrapper = new LambdaQueryWrapper<>();
        String keyword = skuInfoQueryVo.getKeyword();
        String skuType = skuInfoQueryVo.getSkuType();
        Long categoryId = skuInfoQueryVo.getCategoryId();
        queryWrapper.like(!StringUtils.isEmpty(keyword),SkuInfo::getSkuName,keyword);
        queryWrapper.eq(!StringUtils.isEmpty(skuType),SkuInfo::getSkuType,skuType);
        queryWrapper.eq(!StringUtils.isEmpty(categoryId),SkuInfo::getCategoryId,categoryId);
        queryWrapper.orderByAsc(SkuInfo::getSort);
        IPage<SkuInfo> skuPage = baseMapper.selectPage(skuInfoPage, queryWrapper);
        return skuPage;
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void saveSkuInfoVo(SkuInfoVo skuInfoVo) {
        SkuInfo skuInfo = new SkuInfo();
        BeanUtils.copyProperties(skuInfoVo,skuInfo);
        this.save(skuInfo);
        List<SkuPoster> skuPosterList = skuInfoVo.getSkuPosterList();
        if(!Collections.isEmpty(skuPosterList)){
            int sort = 1;
            for (SkuPoster skuPoster : skuPosterList) {
                skuPoster.setSkuId(skuInfo.getId());;

                sort++;
            }
            skuPosterService.saveBatch(skuPosterList);
        }
        List<SkuAttrValue> skuAttrValueList = skuInfoVo.getSkuAttrValueList();
        if(!Collections.isEmpty(skuAttrValueList)){
            int sort = 1 ;
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(skuInfo.getId());;
                skuAttrValue.setSort(sort);
                sort++;
            }
            skuAttrValueService.saveBatch(skuAttrValueList);
        }
        List<SkuImage> skuImagesList = skuInfoVo.getSkuImagesList();
        if (!Collections.isEmpty(skuImagesList)) {
            int sort = 1 ;
            for (SkuImage skuImage : skuImagesList) {
                skuImage.setSkuId(skuInfo.getId());
                skuImage.setSort(sort);
                sort++;
            }
            skuImageService.saveBatch(skuImagesList);
        }

    }

    @Override
    public SkuInfoVo getSkuInfoVo(long id) {
        return getSkuInfoDB(id);
    }

    @Override
    public void check(long skuId, Integer status) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setCheckStatus(status);
        baseMapper.updateById(skuInfo);
    }

    @Override
    public void publish(long id, Integer status) {
        if (status == 1){
            SkuInfo skuInfo = baseMapper.selectById(id);
            skuInfo.setPublishStatus(status);
            baseMapper.updateById(skuInfo);
            rabbitService.sendMessage(MqConst.EXCHANGE_GOODS_DIRECT,MqConst.ROUTING_GOODS_UPPER,id);
        }else {
            SkuInfo skuInfo = baseMapper.selectById(id);
            skuInfo.setPublishStatus(status);
            baseMapper.updateById(skuInfo);
            rabbitService.sendMessage(MqConst.EXCHANGE_GOODS_DIRECT,MqConst.ROUTING_GOODS_LOWER,id);
        }
    }

    @Override
    public void isNewPerson(long id, Integer status) {
        SkuInfo skuInfoUp = new SkuInfo();
        skuInfoUp.setId(id);
        skuInfoUp.setIsNewPerson(status);
        baseMapper.updateById(skuInfoUp);
    }

    private SkuInfoVo getSkuInfoDB(long skuId) {
        SkuInfoVo skuInfoVo = new SkuInfoVo();
        SkuInfo skuInfo = baseMapper.selectById(skuId);
        List<SkuImage> skuImageList = skuImageService.findBySkuId(skuId);
        List<SkuPoster> skuPosterList = skuPosterService.findBySkuId(skuId);
        List<SkuAttrValue> skuAttrValueList = skuAttrValueService.findBySkuId(skuId);
        BeanUtils.copyProperties(skuInfo,skuInfoVo);
        skuInfoVo.setSkuImagesList(skuImageList);
        skuInfoVo.setSkuPosterList(skuPosterList);
        skuInfoVo.setSkuAttrValueList(skuAttrValueList);
        return  skuInfoVo;
    }
}
