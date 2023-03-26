package com.itheima.service.impl;

import com.itheima.annotation.AuthCheck;
import com.itheima.dao.BookDao;
import com.itheima.dao.impl.BookDaoImpl;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    public void save() {
        bookDao.save();
    }

    @AuthCheck()
    public void update() {
        bookDao.update();
    }
}
