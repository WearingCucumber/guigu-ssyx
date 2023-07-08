package com.study.ssyx.product.controller;


import com.atguigu.ssyx.model.product.AttrGroup;
import com.atguigu.ssyx.vo.product.AttrGroupQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.ssyx.common.result.Result;
import com.study.ssyx.product.service.AttrGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 属性分组 前端控制器
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
@Api(tags = "平台属性分组管理")
@RestController
@RequestMapping("/admin/product/attrGroup")
@CrossOrigin
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;
    @ApiOperation("获取属性页")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable long page,
                       @PathVariable long limit,
                       AttrGroupQueryVo attrGroupQueryVo){
        Page<AttrGroup> attrGroupPage = new Page<>(page,limit);
       IPage<AttrGroup>  modelPage =  attrGroupService.getPageList(attrGroupPage,attrGroupQueryVo);
        return  Result.ok(modelPage);
    }
    @ApiOperation("通过id查询")
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable long id){
        AttrGroup attrGroupServiceById = attrGroupService.getById(id);
        return  Result.ok(attrGroupServiceById);
    }
    @ApiOperation("添加")
    @PostMapping("/save")
    public Result save(@RequestBody AttrGroup attrGroup){
        attrGroupService.save(attrGroup);
        return Result.ok(null);
    }
    @ApiOperation("通过id更新数据")
    @PutMapping("/update")
    public Result updateById(@RequestBody AttrGroup attrGroup){
        attrGroupService.updateById(attrGroup);
        return Result.ok(null);
    }
    @ApiOperation("通过id删除")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable long id){
        attrGroupService.removeById(id);
        return  Result.ok(null);
    }
    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result removeRows(@RequestBody List<Long> ids){
        attrGroupService.removeByIds(ids);
        return  Result.ok(null);
    }
    @ApiOperation("查找所有")
    @GetMapping("/findAllList")
    public Result findAllList(){
      List<AttrGroup>  list  =  attrGroupService.findAllList();
      return  Result.ok(list);
    }

}

