package com.study.ssyx.sys.service;

//import com.study.ssyx.sys.entity.Region;
import com.atguigu.ssyx.model.sys.Region;
import com.atguigu.ssyx.model.sys.RegionWare;
import com.atguigu.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-13
 */
public interface RegionService extends IService<Region> {


    List<Region> getRegionByKeyWord(String keyword);
}
