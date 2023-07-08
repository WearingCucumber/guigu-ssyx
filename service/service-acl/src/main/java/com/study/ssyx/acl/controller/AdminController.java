package com.study.ssyx.acl.controller;

import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.ssyx.acl.service.AdminRoleService;
import com.study.ssyx.acl.service.AdminService;
import com.study.ssyx.acl.service.RoleService;
import com.study.ssyx.common.result.Result;
import com.study.ssyx.common.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/admin/acl/user")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private RoleService roleService;
    @ApiOperation("获取某个用户的所有角色")
    @GetMapping("/toAssign/{adminId}")
    public Result toAssign(@PathVariable Long adminId){
        Map<String,Object> map = roleService.getRoleByAdminId(adminId);
        return  Result.ok(map);
    }
    @ApiOperation("为用户进行角色分配")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam Long adminId , @RequestParam Long[] roleId){
        roleService.saveAdminRole(adminId,roleId);
        return Result.ok(null);

    }
    @ApiOperation("分页查询用户列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable long page,
                       @PathVariable long limit,
                       AdminQueryVo adminQueryVo){
        Page<Admin> adminPage = new Page<>(page, limit);
        IPage<Admin> iPage = adminService.selectAdminPage(adminQueryVo,adminPage);
        return  Result.ok(iPage);
    }
    @ApiOperation("根据ID获取某个后台用户")
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable long id){
        Admin admin = adminService.getById(id);
        return  Result.ok(admin);
    }
    @ApiOperation("新增后台用户")
    @PostMapping("/save")
    public Result save(@RequestBody Admin admin){
        String pass = admin.getPassword();
        String password = MD5.encrypt(pass);
        admin.setPassword(password);
        boolean isSucc = adminService.save(admin);
        return isSucc?Result.ok(null):Result.fail(null);
    }
    @ApiOperation("修改用户")
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){
        boolean isSucc = adminService.updateById(admin);
        return isSucc?Result.ok(null):Result.fail(null);
    }
    @ApiOperation("根据id删除用户")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable long id){
        boolean isSucc = adminService.removeById(id);
        return  isSucc?Result.ok(null):Result.fail(null);
    }
    @ApiOperation("批量删除用户")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        boolean isSucc = adminService.removeByIds(ids);
        return isSucc?Result.ok(null):Result.fail(null);
    }
}
