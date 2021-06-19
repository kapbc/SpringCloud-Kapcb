package com.kapcb.ccc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.time.LocalDateTime;

/**
 * <a>Title: ControllerEndPointAspect </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/19 15:39
 */
@Slf4j
@Aspect
public class ControllerEndPointAspect {

    @Around("(@within(org.springframework.stereotype.Controller))||@within(org.springframework.web.bind.annotation.RestController)&&execution(public * com.kapcb.ccc..*.controller..*.*(..))")
    public Object ControllerEndPointAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        LocalDateTime now = LocalDateTime.now();
        long startTime = System.currentTimeMillis();
        Object returnValue;
        Exception exception;
        try {
            returnValue = proceedingJoinPoint.proceed();
            return returnValue;
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            long costTime = System.currentTimeMillis() - startTime;
            log.info("");
        }
    }
}
