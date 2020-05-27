package com.pl.admin.service.cdn;

import com.pl.data.common.api.CommonResult;

import java.io.File;
import java.io.InputStream;

public interface ICdnService {
    CommonResult<String> putObject(String name, File file);
    CommonResult<String> putStream(String name, InputStream stream);
}
