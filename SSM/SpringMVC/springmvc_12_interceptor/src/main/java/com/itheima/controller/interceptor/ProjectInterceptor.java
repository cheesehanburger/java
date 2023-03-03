package com.itheima.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//拦截器的底层就是aop
@Component
public class ProjectInterceptor implements HandlerInterceptor {
    //原始操作之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //request:请求对象
        //response:响应对象
        //handler:被调用的原始方法的封装，本质上是一个方法对象，对反射中的Method对象进行了再包装

        System.out.println("preHandle");
        //这里的返回值可以直接终止原始操作以及后续所有操作
        return true;
    }

    //原始操作之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        //modelAndView:如果处理器执行完成具有返回结果，可以读取到对应数据与页面信息，并进行调整，因为现在都是返回json数据，所以该参数的使用率不高。
        //ex:如果处理器执行过程中出现异常对象，可以针对异常情况进行单独处理

    }

    //原始方法调用完成后执行的内容
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
