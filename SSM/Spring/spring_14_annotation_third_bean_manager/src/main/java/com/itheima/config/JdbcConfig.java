package com.itheima.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.itheima.dao.BookDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
//导入配置文件方法一
//@Configuration
public class JdbcConfig {
    @Value("com.mysql.jdbc.Driver")
    private String driver;
    @Value("jdbc:mysql://localhost:3306/spring_db")
    private String url;
    @Value("root")
    private String username;
    @Value("root")
    private String password;
    //1定义一个方法获得要管理的对象
    //使用@Bean管理第三方bean，意思指定当前的方法返回为一个Bean
    @Bean("dataSource1")
    public DataSource dataSource(BookDao bookDao) {
        //第三方bean依赖注入：引用类型使用方法形参，简单类型使用成员变量
        System.out.println(bookDao);
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
