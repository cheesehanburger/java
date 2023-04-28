package com.itheima;

import com.itheima.dao.UserDao;
import com.itheima.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Mybatisplus04GeneratorApplicationTests {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserDao userDao;
    @Test
    public void test() {
        System.out.println(userService.getById(2));
        userService.
    }
}
