package com.itheima;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

import javax.activation.DataSource;

public class Generator {
    public static void main(String[] args) {
        //1获取代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        DataSourceConfig datasource = new DataSourceConfig();
        datasource.setDriverName("com.mysql.cj.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/mybatisplus_db");
        datasource.setUsername("root");
        datasource.setPassword("admin123");


        autoGenerator.setDataSource(datasource);
        //执行代码生成器
        autoGenerator.execute();


    }
}
