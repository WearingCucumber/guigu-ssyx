package com.study.ssyx.product.controller;


import com.atguigu.ssyx.model.product.Category;
import com.atguigu.ssyx.vo.product.CategoryQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.ssyx.common.result.Result;
import com.study.ssyx.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
@Api(tags = "商品分类管理")
@RestController
@RequestMapping("/admin/product/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //商品分类列表
    @ApiOperation("商品分类列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       CategoryQueryVo categoryQueryVo){
        Page<Category> pageParam = new Page<>(page,limit);
        IPage<Category> modelPage = categoryService.selectPageCategory(pageParam,categoryQueryVo);
        return  Result.ok(modelPage);
    }
    @ApiOperation("通过id查询分类")
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Long id){
        Category cate = categoryService.getById(id);
        return Result.ok(cate);
    }
    @ApiOperation("添加分类")
    @PostMapping("/save")
    public Result save(@RequestBody Category category){
        categoryService.save(category);
        return Result.ok(null);
    }
    @ApiOperation("通过id修改商品")
    @PutMapping("/update")
    public Result updateById(@RequestBody Category category){
        categoryService.updateById(category);
        return Result.ok(null);
    }
    @ApiOperation("通过id 删除商品")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable Long id){
        categoryService.removeById(id);
        return  Result.ok(null);
    }
    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result removeRows(@RequestBody List<Long> ids){
        categoryService.removeByIds(ids);
        return Result.ok(null);
    }
    @ApiOperation("获取全部分类")
    @GetMapping("/findAllList")
    public Result findAllList(){
        return Result.ok(categoryService.findAllList());
    }

}

