package com.study.ssyx.acl.controller;

import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.ssyx.acl.service.RoleService;
import com.study.ssyx.acl.service.impl.RoleServiceImpl;
import com.study.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色接口")
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;
    @ApiOperation("角色条件分页查询")
    //1 角色列表(条件分页查询)
    @GetMapping("/{page}/{limit}")
    public Result pageList(@PathVariable Long page,
                           @PathVariable Long limit,
                           RoleQueryVo roleQueryVo){
        //创建page对象传递当前页面号和每页记录数
        Page<Role> pageParam = new Page<>(page,limit);
        IPage<Role> iPage = roleService.selectRolePage(pageParam,roleQueryVo);
        return Result.ok(iPage);
    }
    //2 根据id查询角色
    @ApiOperation("根据id查询角色")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id){
        Role role = roleService.getById(id);
        return  Result.ok(role);
    }
    //3 添加角色
    @PostMapping("/save")
    @ApiOperation("添加角色")
    public Result save(@RequestBody Role role){
        boolean issave = roleService.save(role);
        return issave?Result.ok(null):Result.fail(null);
    }
    //4 修改角色
    @PutMapping("/update")
    @ApiOperation("修改角色")
    public Result update(@RequestBody Role role){
        boolean b = roleService.updateById(role);
        return  b?Result.ok(null):Result.fail(null);
    }
    //5 更具id删除角色
    @ApiOperation("根据id删除角色")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable long id){
        boolean b = roleService.removeById(id);
        return  b?Result.ok(null):Result.fail(null);
    }
    //6 批量删除角色
    @ApiOperation("批量删除角色")
    @DeleteMapping("/batchRemove")
    public Result removeIds(@RequestBody List<Long> idList){
        boolean b = roleService.removeByIds(idList);
        return b?Result.ok(null):Result.fail(null);

    }
}
