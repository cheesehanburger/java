package com.itheima.service.impl;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.apache.dubbo.config.annotation.Service;


//@Service//将该类的对象创建出来，放到Spring的IOC容器中  bean定义


//将这个类提供的方法（服务）对外发布。将访问的地址 ip，端口，路径注册到注册中心中
@Service (weight = 300)   //weight设置服务权重，默认为100
public class UserServiceImpl implements UserService {

    int i = 1;

    public String sayHello() {
        return "300";
    }

    /*
     * 查询用户
     * */
    public User findUserById(int id) {
        //查询User对象
        User user = new User(1, "laohan", "han123");
        return user;
    }
}
