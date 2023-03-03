package com.itheima.controller;

import com.itheima.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    //@responseBody的作用就是将返回值转换为JSON格式

    //响应页面/跳转页面
    @RequestMapping("/jumpToPage")
    //返回值为String类型，设置返回值为页面名称，即可实现页面跳转
    public String jumpToPage() {
        System.out.println("跳转页面");
        return "page.jsp";
    }

    //响应文本数据
    //返回值为String类型，设置返回值为任意字符串信息，即可实现返回指定字符串信息，需要依赖@ResponseBody注解
    @RequestMapping("/jumpToText")
    @ResponseBody
    public String jumpToText() {
        System.out.println("跳转纯文本");
        return "this is a text";
    }

    //响应POJO对象
    //返回值为实体类对象，设置返回值为实体类类型，即可实现返回对应对象的json数据，需要依赖@ResponseBody注解和@EnableWebMvc注解
    @RequestMapping("toJsonPojo")
    @ResponseBody
    public User toJsonPojo() {
        System.out.println("返回json对象");
        User user = new User();
        user.setName("han");
        user.setAge(15);
        return user;
    }

    //响应POJO集合对象
    //返回值为集合对象，设置返回值为集合类型，即可实现返回对应集合的json数组数据，需要依赖@ResponseBody注解和@EnableWebMvc注解
    @RequestMapping("toJsonPojoList")
    @ResponseBody
    public List<User> toJsonPojoList() {
        System.out.println("返回json对象的list");
        User user1 = new User();
        User user2 = new User();
        user1.setName("han");
        user1.setAge(30);
        user2.setName("kai");
        user2.setAge(24);
        List<User> users= new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        return users;
    }

}
