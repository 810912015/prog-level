package com.pl.admin.service.cdn;

import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import com.pl.data.common.api.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

@Component
public class HwCdnService implements ICdnService {
    private static final Logger log= LoggerFactory.getLogger(HwCdnService.class);
    private static final String endPoint="obs.cn-south-1.myhuaweicloud.com";
    private static final String readEndPoint="progprac.obs.cn-south-1.myhuaweicloud.com";
    private static final String ak="QUJZ3H1LADG04AOJSDYX";
    private static final String sk="pCYp6Rjq8VaWeQlLnuye48ncTRdYePzmuZb3ODWZ";
    private static final String bucket="progprac";
    private ObsClient obsClient;

    public HwCdnService(){
        obsClient=new ObsClient(ak,sk,endPoint);
    }

    @Override
    public CommonResult<String> putObject(String name, File file){
        try {
            PutObjectResult r = obsClient.putObject(bucket, name, file);
            return CommonResult.success(r.getObjectUrl());
        }catch (Exception e){
            log.error("",e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @Override
    public CommonResult<String> putStream(String name, InputStream stream) {
        try {
            PutObjectResult r = obsClient.putObject(bucket, name, stream);
            return CommonResult.success(r.getObjectUrl());
        }catch (Exception e){
            log.error("",e);
            return CommonResult.failed(e.getMessage());
        }
    }
}
