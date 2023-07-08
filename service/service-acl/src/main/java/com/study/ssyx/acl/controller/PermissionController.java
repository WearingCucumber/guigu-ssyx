package com.study.ssyx.acl.controller;

import com.atguigu.ssyx.model.acl.Permission;
import com.study.ssyx.acl.service.PermissionService;
import com.study.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/acl/permission")
@CrossOrigin
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    //分配权限
    @ApiOperation("分配权限")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId){
        List<Permission> permissions = permissionService.getPermissionByAdminId(roleId);
        return  Result.ok(permissions);
    }
    @ApiOperation("保存分配的权限")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam Long roleId ,@RequestParam Long[] permissionId ){
        permissionService.saveRolePermission(roleId,permissionId);
        return  Result.ok(null);
    }
    //查询所有菜单
    @ApiOperation("查询所有菜单")
    @GetMapping
    public Result list(){
        List<Permission> list = permissionService.queryAllPermission();
        return Result.ok(list);
    }
    //新增菜单
    @ApiOperation("新增菜单")
    @PostMapping("/save")
    public Result save(@RequestBody Permission permission){
        permissionService.save(permission);
        return  Result.ok(null);
    }
    //修改菜单
    @ApiOperation("修改菜单")
    @PutMapping("/update")
    public Result update(@RequestBody Permission permission){
        permissionService.updateById(permission);
        return Result.ok(null);
    }
    //删除菜单
    @ApiOperation("递归删除菜单")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id){
        permissionService.removeChildById(id);
        return Result.ok(null);
    }

}
