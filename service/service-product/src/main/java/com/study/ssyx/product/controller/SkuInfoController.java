package com.study.ssyx.product.controller;


import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.vo.product.SkuInfoQueryVo;
import com.atguigu.ssyx.vo.product.SkuInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.ssyx.common.result.Result;
import com.study.ssyx.product.service.SkuInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * sku信息 前端控制器
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
@Api(tags = "sku 管理")
@RestController
@RequestMapping("/admin/product/skuInfo")
@CrossOrigin
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;

    @ApiOperation("商品展示")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable long page,
                       @PathVariable long limit,
                       SkuInfoQueryVo skuInfoQueryVo) {
        Page<SkuInfo> skuInfoPage = new Page<>(page, limit);
        IPage<SkuInfo> pageModel = skuInfoService.getPageList(skuInfoPage, skuInfoQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("通过id查询")
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable long id) {
        SkuInfoVo skuInfoVo = skuInfoService.getSkuInfoVo(id);
        return Result.ok(skuInfoVo);
    }

    @ApiOperation("添加sku")
    @PostMapping("/save")
    public Result save(@RequestBody SkuInfoVo skuInfoVo) {
        skuInfoService.saveSkuInfoVo(skuInfoVo);
        return Result.ok(null);
    }

    @ApiOperation("更新sku")
    @PutMapping("/update")
    public Result update(@RequestBody SkuInfo skuInfo) {
        skuInfoService.updateById(skuInfo);
        return Result.ok(null);
    }

    @ApiOperation("删除单个")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        skuInfoService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result removeRows(@RequestBody List<Long> ids) {
        skuInfoService.removeByIds(ids);
        return Result.ok(null);
    }

    @ApiOperation("商品上架")
    @GetMapping("/publish/{id}/{status}")
    public Result publish(@PathVariable long id,
                          @PathVariable Integer status) {
        skuInfoService.publish(id,status);
        return Result.ok(null);
    }
    @ApiOperation("商品审核")
    @GetMapping("/check/{id}/{status}")
    public Result check(@PathVariable long id ,
                        @PathVariable Integer status){
        skuInfoService.check(id,status);
        return  Result.ok(null);
    }
    @ApiOperation("新人专享")
    @GetMapping("/isNewPerson/{id}/{status}")
    public Result isNewPerson(@PathVariable long id ,
                              @PathVariable Integer status){
        skuInfoService.isNewPerson(id,status);
        return  Result.ok(null);
    }


}

