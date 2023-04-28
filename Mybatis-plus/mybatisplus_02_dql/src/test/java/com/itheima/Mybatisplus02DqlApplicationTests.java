package com.itheima;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.domain.query.UserQuery;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class Mybatisplus02DqlApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void testGetAll() {

        // &lt <=> < ,故lt()是小于查询
        // &gt <=> > ,故gt()是大于查询
        // le ge 分别为小于等于和大于等于


        //todo：按条件查询
        //方式一：按条件查询
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.lt("age",18);
        List<User> users = userDao.selectList(wrapper);
        System.out.println(userDao.selectCount(wrapper));

        //方式二：Lambda格式的条件查询
        //QueryWrapper<User> wrapper = new QueryWrapper<User>();
        //wrapper.lambda().lt(User::getAge, 18);
        //List<User> users = userDao.selectList(wrapper);
        //System.out.println(users);

        //(推荐使用)方式三：lambda格式按条件查询
        //LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        ////使用链式编程
        ////todo 这里是and关系
        ////lqw.lt(User::getAge, 18).gt(User::getAge,3);
        ////todo 这里是or关系
        //lqw.lt(User::getAge, 4).or().gt(User::getAge,15);
        //List<User> users = userDao.selectList(lqw);
        //System.out.println(users);

        ////模拟页面的查询数据
        //UserQuery userQuery = new UserQuery();
        //userQuery.setAge2(30);
        ////userQuery.setAge(10);
        //
        //// todo : 条件查询的空值判定
        //LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        //lqw.gt(userQuery.getAge() != null, User::getAge, userQuery.getAge());
        //lqw.lt(userQuery.getAge2() != null, User::getAge, userQuery.getAge2());
        //
        //List<User> users = userDao.selectList(lqw);
        //System.out.println(users);


        //todo：投影查询
        //LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        //lqw.select(User::getId,User::getName,User::getAge);

        //QueryWrapper qw = new QueryWrapper();
        //qw.select("id","name","age");
        //List<User> users = userDao.selectList(qw);
        //System.out.println(users);

        //QueryWrapper qw = new QueryWrapper();
        //qw.select("id","name","age");
        //List<User> users = userDao.selectList(qw);
        //System.out.println(users);

        //todo: 聚合查询

        //QueryWrapper<User> qw = new QueryWrapper<User>();
        //qw.select("count(*) as total, tel");
        //qw.groupBy("tel");
        //List<Map<String, Object>> users = userDao.selectMaps(qw);
        //System.out.println(users);

        //todo：查询条件

        //等同于=
        //LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        //lqw.eq(User::getName,"韩开元").eq(User::getPassword,"admin123");
        //User user= userDao.selectOne(lqw);
        //System.out.println(user);

        //等同于between
        //LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        //lqw.between(User::getAge,10,30);
        //List<User> users = userDao.selectList(lqw);
        //System.out.println(users);

        //模糊匹配like
        //like():前后加百分号,如 %J%
        //likeLeft():前面加百分号,如 %J
        //likeRight():后面加百分号,如 J%
        //LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        //lqw.likeLeft(User::getName,"J");
        //List<User> users = userDao.selectList(lqw);
        //System.out.println(users);

       //排序查询
       // LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
       // lqw.orderBy(true,false,User::getId);
       // List<User> users = userDao.selectList(lqw);
       // System.out.println(users);
    }
}
