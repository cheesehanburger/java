package com.itheima.service.impl;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.apache.dubbo.config.annotation.Service;


//@Service//将该类的对象创建出来，放到Spring的IOC容器中  bean定义

/*
* timeout 超时时间 （默认为1000ms）
* retries 重试次数  （默认为2次，重试次数 + 1 = 总请求次数）
* */
//将这个类提供的方法（服务）对外发布。将访问的地址 ip，端口，路径注册到注册中心中
@Service(timeout = 3000, retries = 2)  // 超时时间建议定义在服务端，用来断开线程，防止雪崩，当前服务3s超时，重试两次，总共三次

public class UserServiceImpl implements UserService {

    int i = 1;

    public String sayHello() {
        return "hello dubbo hello!~";
    }

    /*
     * 查询用户
     * */
    public User findUserById(int id) {
        System.out.println("服务被调用了：" + i++);
        //查询User对象
        User user = new User(1, "laohan", "han123");
        //假设数据查询很慢，等待了5s；
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return user;
    }
}
