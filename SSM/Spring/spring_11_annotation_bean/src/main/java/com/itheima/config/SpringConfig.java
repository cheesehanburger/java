package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//注解文件的结构转化为@Configuration
@Configuration
//配置文件中的扫描转化为@ComponentScan
@ComponentScan({"com.itheima.dao","com.itheima.service"})
//配置类的作用仅仅是吧配置文件的信息换了一种形式进行描述
public class SpringConfig {
}
