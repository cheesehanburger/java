package com.itheima.config;

import com.itheima.controller.interceptor.ProjectInterceptor;
import com.itheima.controller.interceptor.ProjectInterceptor2;
import com.itheima.controller.interceptor.ProjectInterceptor3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan({"com.itheima.controller"})
@EnableWebMvc

//拦截器配置方式二
//实现WebMvcConfigurer接口可以简化开发（类似于代替SpringMvcSupport），但具有一定的侵入性
public class SpringMvcConfig implements WebMvcConfigurer {
    //注入拦截器
    @Autowired
    private ProjectInterceptor projectInterceptor;
    @Autowired
    private ProjectInterceptor2 projectInterceptor2;
    @Autowired
    private ProjectInterceptor3 projectInterceptor3;

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置拦截目录，使用registry对象配置
        //拦截器会根据书写的顺序执行
        registry.addInterceptor(projectInterceptor).addPathPatterns("/books", "/books/*");
        registry.addInterceptor(projectInterceptor2).addPathPatterns("/books", "/books/*");
        registry.addInterceptor(projectInterceptor3).addPathPatterns("/books", "/books/*");
    }
}