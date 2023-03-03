package com.itheima.service;


import com.itheima.config.SpringConfig;
import com.itheima.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Test
    public void TestSave() {
        Book book = new Book();
        book.setName("海上灵光");
        book.setType("摄影文学");
        book.setDescription("许多的念念不忘只是一瞬，许多的一瞬却念念不忘");
        bookService.save(book);
    }

    @Test
    public void TestById(){
       Book book =bookService.selectById(3);
        System.out.println(book);
    }

    @Test
    public void TestAll() {
        List<Book> books = bookService.selectAll();
        System.out.println(books);
    }
}
