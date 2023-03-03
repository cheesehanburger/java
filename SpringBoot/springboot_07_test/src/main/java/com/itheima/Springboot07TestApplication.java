package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//引导类(application.class)所在包必须是测试类所在包及其子包,才能正常的运行测试环境
@SpringBootApplication
public class Springboot07TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot07TestApplication.class, args);
    }

}
