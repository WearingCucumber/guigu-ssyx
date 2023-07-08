package com.study.ssyx.sys.controller;


import com.atguigu.ssyx.model.sys.Ware;
import com.study.ssyx.common.result.Result;
import com.study.ssyx.sys.service.WareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-13
 */
@Api(tags = "仓库管理")
@RestController
@RequestMapping("/admin/sys/ware")
@CrossOrigin
public class WareController {
    @Autowired
    private WareService wareService;
    @ApiOperation("查询所有仓库")
    @GetMapping("/findAllList")
    public Result findAllList(){
        List<Ware> list = wareService.list();
        return Result.ok(list);
    }

}

