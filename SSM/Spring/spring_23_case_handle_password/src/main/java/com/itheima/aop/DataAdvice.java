package com.itheima.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataAdvice {
    @Pointcut("execution(boolean com.itheima.service.*Service.openURL(..))")
    private void servicePt() {
    }

    @Around("servicePt()")
    public Object trimStr(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                args[i] = ((String) args[i]).trim();
            }
        }
        Object ret = null;
        try {
            ret = pjp.proceed(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return ret;
    }
}
