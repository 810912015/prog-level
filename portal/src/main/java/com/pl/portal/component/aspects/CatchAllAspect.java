package com.pl.portal.component.aspects;

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
public class CatchAllAspect {
    private static final Logger logger= LoggerFactory.getLogger(CatchAllAspect.class);
    @Pointcut("execution(public * com.pl.portal.component..*(..))||" +
            "execution(public * com.pl.portal.service..*(..))")
    public void catchAll(){}

    @Around("catchAll()")
    public Object around(ProceedingJoinPoint joinPoint){
        try{
            return joinPoint.proceed();
        }catch (Throwable e){
            logger.error("",e);
            return null;
        }
    }
}
