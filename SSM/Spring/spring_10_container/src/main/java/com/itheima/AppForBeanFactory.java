package com.itheima;

import com.itheima.dao.BookDao;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
//初始化BeanFactory(最早期的初始化方案)
public class AppForBeanFactory {
    public static void main(String[] args) {
        //BeanFactory与ApplicationContext的区别就是：它们加载的时期不同，BeanFactory会延迟加载bean，而ApplicationContext会立即加载bean
        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory bf = new XmlBeanFactory(resource);  //还未加载bean
        //BookDao bookDao = (BookDao) bf.getBean(BookDao.class);
        //bookDao.save();
    }
}
