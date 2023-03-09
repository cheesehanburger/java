package com.itheima;

import com.topath.dao.UserDao;
import com.topath.domain.User;
import com.topath.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Mybatisplus04GeneratorApplicationTests {
    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        User user = new User();
        user.setId(10L);
        user.setName("han");
        user.setAge(12);
        user.setTel("122243433");
        user.setPwd("adadsdsdsdd");
        System.out.println( userDao.selectList(null));
    }

}
