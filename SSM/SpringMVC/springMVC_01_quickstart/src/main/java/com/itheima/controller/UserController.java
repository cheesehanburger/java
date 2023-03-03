package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//这里是开发常更改的地方
//2.1声明为一个bean
@Controller
public class UserController {
    //2.2设置当前操作的访问路径
    @RequestMapping("/save")
    //2.3设置当前操作的返回值类型，会将当前函数的返回值返回给请求网页
    @ResponseBody
    public String save() {
        System.out.println("user save...");
        return "{'module':'springmvc'}";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String Delete() {
        System.out.println("user delete...");
        return "{'module':'springmvc delete'}";
    }
}
