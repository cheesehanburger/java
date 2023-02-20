package com.itheima;

import com.itheima.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        //两种容器的创建方式
        //1.加载类路径下的配置文件(常用)
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.从文件系统下加载配置文件(了解即可)
        //ApplicationContext ctx = new FileSystemXmlApplicationContext("/Users/kaiyuan/Documents/projects/java/SSM/Spring/spring_10_container/applicationContext.xml");

        //第一种获取bean的方式：需要强制转换
        //BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        //第二种获取bean的方式：需要指定类型
        //BookDao bookDao = ctx.getBean("bookDao",BookDao.class);
        //第三种获取bean的方式：直接按类型查找，需要保证只有唯一类型的bean
        //BookDao bookDao = ctx.getBean(BookDao.class);
        //bookDao.save();
    }
}
