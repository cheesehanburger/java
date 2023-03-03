package com.itheima.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component     //将通知类配给容器
@Aspect        //并且标识为切面类
//通知类
public class MyDevice {
    //AOP的本质就是代理模式
    //被实例化bean对象的类中的方法和切入点进行匹配
    //如果切入点能匹配成功则使用代理对象，如果未能匹配成功则使用原对象

    //切入点：需要增强的方法（可以描述接口或者实现类）
    @Pointcut("execution(void com.itheima.dao.BookDao.update())")
    private void pt() {
    }


    @Before("pt()")   //@Before会在切入点方法之前执行
    //通知：存放共性功能的方法
    public void method() {
        System.out.println(System.currentTimeMillis());
    }
}
