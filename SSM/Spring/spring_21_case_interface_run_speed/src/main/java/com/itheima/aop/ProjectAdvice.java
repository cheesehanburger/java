package com.itheima.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProjectAdvice {

    //匹配业务层的所有方法
    @Pointcut("execution(* com.itheima.service.*Service.*(..))")
    private void pt() {
    }

    @Around("pt()")
    public void runSpeed(ProceedingJoinPoint pjp) throws Throwable {
        //封装了执行方法的签名信息
        Signature signature = pjp.getSignature();
        //通过签名获取执行操作名称(接口名)
        String className = signature.getDeclaringTypeName();
        //通过签名获取执行操作名称(方法名)
        String methodName = signature.getName();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Object result = pjp.proceed();
        }
        long end = System.currentTimeMillis();
        System.out.println(className + methodName + " 的万次执行时间: "+ (end - start) + "ms");
    }
}
