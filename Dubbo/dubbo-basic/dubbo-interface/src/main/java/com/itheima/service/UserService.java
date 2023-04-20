package com.itheima.service;


//由于消费者远程调用生产的的服务的时候，需要创建与生产者一样的接口，为了方便，遂抽离出interface模块，单独存放接口，供生产者消费者共同使用
public interface UserService {


    public String sayHello();
}
