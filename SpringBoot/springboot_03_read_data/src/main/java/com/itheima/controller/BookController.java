package com.itheima.controller;

import com.itheima.domain.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    @Value("${lesson}")
    private String lesson;
    @Value("${server.port}")
    private Integer port;
    @Value("${enterprise.subject[0]}")
    private String subject_0;

    @Autowired
    private Environment environment;  //这个对象会加载所有的环境信息

    @Autowired
    private Enterprise enterprise;

    @GetMapping("{id}")
    public String getById(@PathVariable Integer id) {
        //使用普通属性
        System.out.println(lesson);
        System.out.println(port);
        System.out.println(subject_0);
        System.out.println("--------------------------");
        //使用环境对象
        System.out.println(environment.getProperty("lesson"));
        System.out.println(environment.getProperty("server.port"));
        System.out.println(environment.getProperty("enterprise.age"));
        System.out.println(environment.getProperty("enterprise.subject[1]"));
        System.out.println("--------------------------");
        //使用自定义实体对象(常用)
        System.out.println(enterprise.getName());
        System.out.println(enterprise.getName());
        System.out.println(enterprise.getTel());
        System.out.println(enterprise.getSubject()[2]);
        return "hello, springboot !";
    }


}


