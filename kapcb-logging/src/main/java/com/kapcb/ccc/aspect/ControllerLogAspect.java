package com.kapcb.ccc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * <a>Title: ControllerLogAspect </a>
 * <a>Author: Kapcb <a>
 * <a>Description:  <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/14 19:25
 */
@Slf4j
@Aspect
public class ControllerLogAspect {

    @Around("(@within(org.springframework.stereotype.Controller))||@within(org.springframework.web.bind.annotation.RestController)&&execution(public * com.kapcb.ccc..*.controller..*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();
        Object returnValue = null;
        Exception exception = null;
        try {
            returnValue = proceedingJoinPoint.proceed();
            return returnValue;
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            long costTime = System.currentTimeMillis() - startTime;
            if (exception != null) {
                log.error("[class: {}][method: {}][cost: {}ms][args: {}][发生异常]", className, methodName, proceedingJoinPoint.getArgs(), exception);
            } else {
                log.info("[class: {}][method: {}][cost: {}ms][args: {}][return: {}]", className, methodName, costTime, proceedingJoinPoint.getArgs(), returnValue);
            }
        }
    }
}
