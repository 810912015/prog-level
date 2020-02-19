package com.pl.data.common.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class BaseController {
    protected static Logger logger= LoggerFactory.getLogger("controller");
    protected <T> CommonResult<T> toResult(Callable<T> c){
        try{
            return CommonResult.success(c.call());
        }catch (Exception e){
            logger.error("",e);
            return CommonResult.failed(e.getMessage());
        }
    }
}
