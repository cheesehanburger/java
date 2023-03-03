package com.itheima.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

//4.定义一个servlet容器启动的配置类，在里面加载spring的配置
public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {
    //加载springMVC容器
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        //创建WebApplicationContext对象
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        //加载指定的配置类
        ctx.register(SpringMvcConfig.class);
        return ctx;
    }
    //设置哪些请求归属于springMVC处理
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    //加载spring容器配置
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
