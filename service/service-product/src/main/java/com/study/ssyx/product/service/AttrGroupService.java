package com.study.ssyx.product.service;

//import com.study.ssyx.product.entity.AttrGroup;
import com.atguigu.ssyx.model.product.AttrGroup;
import com.atguigu.ssyx.vo.product.AttrGroupQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
public interface AttrGroupService extends IService<AttrGroup> {

    IPage<AttrGroup> getPageList(Page<AttrGroup> attrGroupPage, AttrGroupQueryVo attrGroupQueryVo);

    List<AttrGroup> findAllList();
}
