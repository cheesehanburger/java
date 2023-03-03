package com.itheima.config;

import com.itheima.controller.interceptor.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//拦截器配置方式一：
@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {
    @Autowired
    private ProjectInterceptor projectInterceptor;

    //放行目录
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages").addResourceLocations("/pages/");
    }

    //拦截器目录
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //拦截目录
        registry.addInterceptor(projectInterceptor).addPathPatterns("/books","/books/*");
    }
}
