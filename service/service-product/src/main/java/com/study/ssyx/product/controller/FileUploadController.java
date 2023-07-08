package com.study.ssyx.product.controller;

import com.study.ssyx.common.result.Result;
import com.study.ssyx.product.service.FileUploadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
    @PostMapping("/fileUpload")
    public Result fileUpload(MultipartFile file) throws Exception {
        return  Result.ok(fileUploadService.fileUpload(file));

    }
}
