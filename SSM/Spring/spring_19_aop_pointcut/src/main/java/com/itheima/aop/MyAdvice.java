package com.itheima.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {
    //切入点表达式：
    //@Pointcut("execution(* com.itheima.dao.impl.BookDaoImpl.update(*))")   //返回值任意，但是update方法必须要有一个参数，无法匹配，要想匹配需要在update接口和实现类添加参数
    //@Pointcut("execution(void com.*.*.*.*.update())")    //返回值为void,com包下的任意包三层包下的任意类的update方法，匹配到的是实现类，能匹配
    //@Pointcut("execution(void com.*.*.*.update())")      //返回值为void,com包下的任意两层包下的任意类的update方法，匹配到的是接口，能匹配
    // @Pointcut("execution(void *..update())")      //返回值为void，方法名是update的任意包下的任意类，能匹配
    // @Pointcut("execution(* *..*(..))")      //匹配项目中任意类的任意方法，能匹配，但是不建议使用这种方式，影响范围广
    // @Pointcut("execution(* *..u*(..))")      //匹配项目中任意包任意类下只要以u开头的方法，update方法能满足，能匹配
    //@Pointcut("execution(* *..*e(..))")      //匹配项目中任意包任意类下只要以e结尾的方法，update和save方法能满足，能匹配
    @Pointcut("execution(* com.itheima.*.*Service.*e(..))")      //将项目中所有业务层方法的以e结尾的方法匹配
    private void pt() {
    }

    @Before("pt()")
    public void method() {
        System.out.println(System.currentTimeMillis());
    }
}
