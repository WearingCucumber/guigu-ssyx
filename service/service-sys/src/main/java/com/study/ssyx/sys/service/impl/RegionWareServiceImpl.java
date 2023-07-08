package com.study.ssyx.sys.service.impl;

//import com.study.ssyx.sys.entity.RegionWare;
import com.atguigu.ssyx.model.sys.RegionWare;
import com.atguigu.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.ssyx.common.exception.SsyxException;
import com.study.ssyx.common.result.ResultCodeEnum;
import com.study.ssyx.sys.mapper.RegionWareMapper;
import com.study.ssyx.sys.service.RegionWareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 城市仓库关联表 服务实现类
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-13
 */
@Service
public class RegionWareServiceImpl extends ServiceImpl<RegionWareMapper, RegionWare> implements RegionWareService {


    @Override
    public IPage<RegionWare> selectPageRegionWare(Page<RegionWare> regionWarePage, RegionWareQueryVo regionWareQueryVo) {
        LambdaQueryWrapper<RegionWare> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(regionWareQueryVo.getKeyword()),RegionWare::getRegionName,regionWareQueryVo.getKeyword()).
                or().like(!StringUtils.isEmpty(regionWareQueryVo.getKeyword()),RegionWare::getWareName,regionWareQueryVo.getKeyword());
        IPage<RegionWare> page = baseMapper.selectPage(regionWarePage, queryWrapper);
        return page;
    }

    @Override
    public void saveRegionWare(RegionWare regionWare) {
        LambdaQueryWrapper<RegionWare> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RegionWare::getRegionId,regionWare.getRegionId());
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count>0){
            //已经存在
            throw  new SsyxException(ResultCodeEnum.REGION_OPEN);
        }else{
            baseMapper.insert(regionWare);
        }
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        RegionWare regionWare = baseMapper.selectById(id);
        regionWare.setStatus(status);
        baseMapper.updateById(regionWare);
    }
}
