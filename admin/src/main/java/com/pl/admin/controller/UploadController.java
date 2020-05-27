package com.pl.admin.controller;

import com.pl.admin.service.cdn.ICdnService;
import com.pl.data.common.api.CommonResult;
import io.swagger.annotations.Api;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@Controller
@Api(tags = "UploadController",description = "UploadController api")
@RequestMapping("/admin/upload")
public class UploadController {
    @RequestMapping("/files")
    @ResponseBody
    public CommonResult<String> uploadFile(@RequestParam("file")MultipartFile file) {
        CommonResult<String> tr = null;
        try {
            tr = cdnService.putStream(file.getOriginalFilename(), file.getInputStream());
            return CommonResult.success(tr.getData());
        } catch (IOException e) {
            LoggerFactory.getLogger(getClass()).error("uploadFile", e);
            return CommonResult.failed(e.getMessage());
        }

    }
    @Autowired
    private ICdnService cdnService;
}
