package com.itheima;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

// Mapper代理开发
public class MybatisDemo2 {
    public static void main(String[] args) throws IOException {

        // 1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Usermapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectById(2);
        System.out.println(user);
        //4.释放资源
        sqlSession.close();
    }
}
