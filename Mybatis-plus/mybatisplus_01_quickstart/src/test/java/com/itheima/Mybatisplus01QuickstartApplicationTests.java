package com.itheima;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mybatisplus01QuickstartApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void testGetAll() {
        List<User> users = userDao.selectList(null);
        System.out.println(users);
    }

    @Test
    void testAdd() {
        User user = new User();
        user.setName("韩开元");
        user.setAge(23);
        user.setTel("1233211122");
        user.setPassword("admin123");
        int count = userDao.insert(user);
        System.out.println(count);
    }

    @Test
    void testDelete() {
        int count = userDao.deleteById(5);
        System.out.println(count);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1631835760229457921L);
        user.setName("吴彦祖");
        user.setAge(45);
        int count = userDao.updateById(user);
        System.out.println(count);
    }

    @Test
    void getById() {
        User user = userDao.selectById(3);
        System.out.println(user);
    }

    @Test
    void testGetPage() {
        //使用分页功能之前，需要配置拦截器

        IPage page = new Page(2,2);
        userDao.selectPage(page,null);
        System.out.println("当前页码值：" + page.getCurrent());
        System.out.println("每页显示数：" + page.getSize());
        System.out.println("一共多少页：" + page.getPages());
        System.out.println("一共多少条数据：" + page.getTotal());
        System.out.println("数据：" + page.getRecords());
    }
}
