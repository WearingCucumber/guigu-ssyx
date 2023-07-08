package product.controller;

import com.atguigu.ssyx.vo.product.CategoryQueryVo;
import com.study.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import product.service.CategoryService;

@Api(value = "Category管理",tags = "商品分类")
@RestController
@CrossOrigin
@RequestMapping("/admin/product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/{page}/{limit}")
    @ApiOperation("获取商品分类分页列表")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        CategoryQueryVo categoryQueryVo){

        return  null;
    }

}
