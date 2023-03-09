package com.itheima;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Mybatisplus03DqlApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void TestAdd() {
        User user = new User();
        //user.setId(10L);
        user.setName("黑马程序员");
        user.setPassword("itheima");
        user.setAge(12);
        user.setTel("4006184000");
        int count = userDao.insert(user);
        System.out.println(count);
    }

    @Test
    public void TestDelete() {
        //List<Long> ids = new ArrayList<Long>();
        //ids.add(1L);
        //ids.add(2L);
        //ids.add(3L);
        //根据多个id进行多记录删除/查询
        //userDao.deleteBatchIds(ids);
        //System.out.println(userDao.selectBatchIds(ids));

        //todo： 逻辑删除
        //因为设置了逻辑删除，这里的删除已经不是真正删除原数据，而是标记删除
        userDao.deleteById(7);
        //并且，查询的时候，会自动舍去标记为删除的数据
        System.out.println(userDao.selectList(null));

    }

    @Test
    public void TestUpdate() {
        //User user = new User();
        //user.setId(3L);
        //user.setName("Marry");
        //user.setVersion(1);
        //userDao.updateById(user);

        ////1，先通过修改当前的数据id将当前的数据查询出来(目的是为了获取version值)
        //User user = userDao.selectById(3L);
        ////2，将需要修改的属性逐一设置进去
        //user.setName("Jock");
        ////3，然后提交修改
        //userDao.updateById(user);

    //    模拟并发问题

        User user1 = userDao.selectById(3L);
        User user2 = userDao.selectById(3L);

        user1.setName("jerry 01");
        user2.setName("jerry 02");

        userDao.updateById(user1);
        userDao.updateById(user2);

    }
}
