package com.itheima.service.impl;

import com.itheima.dao.BookDao;
import com.itheima.service.BookService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//使用生命周期方式二：使用接口
public class BookServiceImpl implements BookService, InitializingBean,DisposableBean {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        System.out.println("set .....");
        this.bookDao = bookDao;
    }

    public void save() {
        //System.out.println("book service save ...");
        bookDao.save();
    }

    public void destroy() throws Exception {
        System.out.println("service destory");
    }
    //当属性设置完成以后，才会执行该方法
    public void afterPropertiesSet() throws Exception {
        System.out.println("service init");
    }
}
