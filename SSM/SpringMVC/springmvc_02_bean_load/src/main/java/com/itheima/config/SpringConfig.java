package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
//避免Spring错误加载到SpringMVC的bean
//方法一：使用具体的配置(开发中常用这种)
//@ComponentScan({"com.itheima.service","com.itheima.dao"})
//方法二：使用注解排斥
@ComponentScan(value = "com.itheima",
        excludeFilters = @ComponentScan.Filter(
                //设置过滤类型
                type = FilterType.ANNOTATION,
                //设置排除的具体注解类
                classes = Controller.class
        )
)
public class SpringConfig {
}
