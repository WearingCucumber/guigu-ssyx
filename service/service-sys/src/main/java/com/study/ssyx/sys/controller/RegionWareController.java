package com.study.ssyx.sys.controller;


import com.atguigu.ssyx.model.sys.RegionWare;
import com.atguigu.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.ssyx.common.result.Result;
import com.study.ssyx.sys.service.RegionService;
import com.study.ssyx.sys.service.RegionWareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 城市仓库关联表 前端控制器
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-13
 */
@Api(tags = "开通区域")
@CrossOrigin
@RestController
@RequestMapping("/admin/sys/regionWare")
public class RegionWareController {
    @Autowired
    private RegionWareService regionWareService;
    @ApiOperation("开通区域列表")
    @GetMapping("/{page}/{limit}")
    public Result page(@PathVariable Long page , @PathVariable Long limit, RegionWareQueryVo regionWareQueryVo){
        Page<RegionWare> regionWarePage =  new Page<>(page, limit);
        IPage<RegionWare> pageModel = regionWareService.selectPageRegionWare(regionWarePage,regionWareQueryVo);
        return Result.ok(pageModel);
    }
    @ApiOperation("添加开通区域")
    @PostMapping("/save")
    public Result save(@RequestBody RegionWare regionWare){
        regionWareService.saveRegionWare(regionWare);
        return Result.ok(null);
    }
    @ApiOperation("删除开通区域")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable Long id){
        regionWareService.removeById(id);
        return Result.ok(null);
    }
    @ApiOperation("取消开通区域")
    @PostMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id , @PathVariable Integer status){
        regionWareService.updateStatus(id,status);
        return  Result.ok(null);
    }

}

