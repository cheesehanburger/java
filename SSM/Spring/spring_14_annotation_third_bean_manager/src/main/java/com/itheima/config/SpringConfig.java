package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//导入配置文件方法一
@ComponentScan("com.itheima")
//建议这一种方法
//@Import注解手动引入需要加载的配置类，参数是一个数组，全局只能使用一次
@Import({JdbcConfig.class})
public class SpringConfig {

}
