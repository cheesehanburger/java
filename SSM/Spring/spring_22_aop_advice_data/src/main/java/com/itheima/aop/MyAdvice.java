package com.itheima.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(* com.itheima.dao.BookDao.findName(..))")
    private void pt() {
    }

    //@Before("pt()")
    public void before(JoinPoint jp) {
        Object[] args = jp.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("before advice ...");
    }

    // @After("pt()")
    public void after(JoinPoint jp) {
        System.out.println("after advice ...");
        Object[] args = jp.getArgs();
        System.out.println(Arrays.toString(args));
    }

     //@Around("pt()")
    public Object around(ProceedingJoinPoint pjp){
        Object[] args = pjp.getArgs();
        args[0] = 666;
        Object ret = null;
        //使用try catch块捕获原方法的异常
        try {
            //proceed()方法内部可以填写数据，并且会覆盖原数据
            ret = pjp.proceed(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return ret;
    }

    //注意：1：returning：设置返回后通知获取原始方法的返回值，要求returning属性值必须与方法形参名相同
    //2.如果有JoinPoint参数，参数必须放在第一位
    @AfterReturning(value = "pt()", returning = "ret")
    public void afterReturning(JoinPoint jp,Object ret) {
        System.out.println("afterReturning advice ..." + ret);
        System.out.println(Arrays.toString(jp.getArgs()));
    }

    //注意：设置抛出异常后通知获取原始方法运行时抛出的异常对象，要求throwing属性值必须与方法形参名相同
    //@AfterThrowing(value = "pt()",throwing = "t")
    public void afterThrowing(Throwable t) {
        System.out.println("afterThrowing advice ..." + t);
    }
}
