package com.itheima.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(void com.itheima.dao.BookDao.update())")
    private void pt() {
    }

    @Pointcut("execution(int com.itheima.dao.BookDao.select())")
    private void pt2() {
    }

    //前置通知
    // @Before("pt()")
    public void before() {
        System.out.println("before advice ...");
    }

    //后置通知
    //@After("pt2()")
    public void after() {
        System.out.println("after advice ...");
    }

    //环绕通知（重点）
    // 注意 如果没有使用ProceedingJoinPoint对原始方法进行调用，则会直接跳过原始方法
    // @Around("pt()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice ...");
        //必须有一个表示对原始操作的调用
        pjp.proceed();
        System.out.println("around after advice ...");
    }

    // @Around("pt2()")
    public Object aroundSelect(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice ...");
        Object result = pjp.proceed();
        System.out.println("around after advice ...");
        //如果原方法有返回值，则需要接受后返回
        return result;
    }

    //返回后通知(了解) 只会在原方法不抛出异常后才会使用
    //@AfterReturning("pt2()")
    public void afterReturning() {
        System.out.println("afterReturning advice ...");
    }

    //抛出异常后运行
    //@AfterThrowing("pt2()")
    public void afterThrowing() {
        System.out.println("afterThrowing advice ...");
    }
}
