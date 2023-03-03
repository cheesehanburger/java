package com.itheima.controller;

import com.itheima.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


//注：requestParam接受非json数据，requestBody接受json格式（常用）
//请求参数
@Controller
public class UserController {

    //todo 五种参数类型的接受
    //普通参数
    @RequestMapping("/commonParam")
    @ResponseBody
    //直接使用形参接受(get请求和post请求都可以)
    public String commonParam(String name, int age) {
        System.out.println("普通参数：" + name);
        System.out.println("普通参数：" + age);
        return "{'module':'common param'}";
    }

    //普通参数 (请求参数和形参名不同)
    @RequestMapping("/commonParamDifferentName")
    @ResponseBody
    //如果请求参数和形参名不同则使用@RequestParam指定对应的请求参数，才能正确的接受
    public String commonParamDifferentName(@RequestParam("name") String username, int age) {
        System.out.println("普通参数：" + username);
        System.out.println("普通参数：" + age);
        return "{'module':'common ParamDifferentName'}";
    }

    //pojo参数
    //如过请求的参数名和pojo类的属性名一样，则会把传递的值自动注入给形参对象
    @RequestMapping("/pojoParam")
    @ResponseBody
    public String pojoParam(User user) {
        System.out.println("pojo参数传递" + user);
        return "{'module':'pojo Param'}";
    }

    //pojo参数嵌套POJO数据
    @RequestMapping("/pojoContainPojoParam")
    @ResponseBody
    public String pojoContainPojoParam(User user) {
        System.out.println("pojo参数传递" + user);
        return "{'module':'pojo Param'}";
    }

    //数组参数
    @RequestMapping("/arrayParam")
    @ResponseBody
    public String arrayParam(String[] likes) {
        System.out.println("数组参数传递" + Arrays.toString(likes));
        return "{'module':'array Param'}";
    }

    //集合参数
    //SpringMVC将List看做是一个POJO对象来处理，将其创建一个对象并准备把前端的数据封装到对象中，但是List是一个接口无法创建对象
    // 同名请求参数可以使用@RequestParam注解映射到对应名称的集合对象中作为数据
    @RequestMapping("/listParam")
    @ResponseBody
    public String listParam(@RequestParam List<String> likes) {
        System.out.println("数组参数传递" + likes);
        return "{'module':'list Param'}";
    }


    //todo 接受json格式

    //集合参数(json格式)
    @RequestMapping("/listParamForJson")
    @ResponseBody
    //在参数前一定要加@RequestBody接受json格式
    public String listParamForJson(@RequestBody List<String> likes) {
        System.out.println("list common(json)参数传递 list ==> " + likes);
        return "{'module':'list common for json param'}";
    }

    //pojo参数(json格式)
    @RequestMapping("/pojoParamForJson")
    @ResponseBody
    public String pojoParamForJson(@RequestBody User user) {
        System.out.println("pojo(json)参数传递" + user);
        return "{'module':'pojo for json param'}";
    }

    //集合参数(json格式)
    @RequestMapping("/listPojoParamForJson")
    @ResponseBody
    public String listPojoParamForJson(@RequestBody List<User> list) {
        System.out.println("list common(json)参数传递 list ==> " + list);
        return "{'module':'list pojo for json param'}";
    }

    //日期格式可以直接传递
    //但是springMVC默认只支持yyyy/MM/dd的时间格式，需要另外的解析格式可以通过@DateTimeFormat
    @RequestMapping("/dateParam")
    @ResponseBody
    public String dataParam(Date date,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") Date date2){
        System.out.println("date参数传递: " + date);
        System.out.println("date1参数传递: " + date1);
        System.out.println("date2参数传递: " + date2);
        return "{'module':'date param'}";
    }
}











