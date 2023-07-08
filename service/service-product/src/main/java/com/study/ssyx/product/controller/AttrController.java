package com.study.ssyx.product.controller;


import com.atguigu.ssyx.model.product.Attr;
import com.study.ssyx.common.result.Result;
import com.study.ssyx.product.service.AttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
@Api(tags = "平台属性管理")
@RestController
@RequestMapping("/admin/product/attr")
@CrossOrigin
public class AttrController {
    @Autowired
    private AttrService attrService;
    @ApiOperation("通过组id查询所有属性")
    @GetMapping("/{groupId}")
    public Result getList(@PathVariable Long groupId){
        List<Attr> list =  attrService.getList(groupId);
        return  Result.ok(list);
    }
    @ApiOperation("通过id查询")
    @GetMapping("/get/{id}")
    public  Result getById(@PathVariable long id){
        Attr attrServiceById = attrService.getById(id);
        return  Result.ok(attrServiceById);
    }
    @ApiOperation("保存")
    @PostMapping("/save")
    public Result save(@RequestBody Attr attr){
        attrService.save(attr);
        return Result.ok(null);
    }
    @ApiOperation("根据id更新")
    @PutMapping("/update")
    public Result updateById(@RequestBody Attr attr){
        attrService.updateById(attr);
        return  Result.ok(null);
    }
    @ApiOperation("通过id删除")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id){
        attrService.removeById(id);
        return  Result.ok(null);
    }
    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result removeRows(@RequestBody List<Long> ids){
        attrService.removeByIds(ids);
        return Result.ok(null);
    }




}

