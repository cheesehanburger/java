package com.itheima.service.impl;

import com.itheima.dao.BookDao;
import com.itheima.dao.impl.BookDaoImpl;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookServiceImpl implements BookService {
    ////用来自动装配
    @Autowired
    ////用来指定装配哪个bean
    @Qualifier("bookDao1")
    private BookDao bookDao;



    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }
}
