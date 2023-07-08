package com.study.ssyx.sys.controller;


import com.atguigu.ssyx.model.sys.Region;
import com.study.ssyx.common.result.Result;
import com.study.ssyx.sys.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-13
 */
@Api(tags = "地区管理")
@RestController
@RequestMapping("/admin/sys/region")
@CrossOrigin
public class RegionController {
    @Autowired
    private RegionService regionService;
    @ApiOperation("根据区域关键字查询区域列表信息")
    @GetMapping("/findRegionByKeyword/{keyword}")
    public Result findRegionByKeyword(@PathVariable String keyword){
        List<Region> list  = regionService.getRegionByKeyWord(keyword);
        return  Result.ok(list);
    }

}

