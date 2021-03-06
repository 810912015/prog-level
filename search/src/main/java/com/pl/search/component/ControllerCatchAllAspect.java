package com.pl.search.component;


import com.pl.data.common.api.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ControllerCatchAllAspect {
    private static final Logger logger= LoggerFactory.getLogger(ControllerCatchAllAspect.class);
    @Pointcut("execution(public * com.pl.search.controller..*(..))")
    public void catchAll(){}

    @Around("catchAll()")
    public Object around(ProceedingJoinPoint joinPoint){
        try{
            return joinPoint.proceed();
        }catch (Throwable e){
            logger.error("",e);
            return new Result("异常,请查看日志");
        }
    }
}
