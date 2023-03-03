package com.itheima.controller;


import com.itheima.domain.Book;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping
    public boolean save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping
    public boolean update(@RequestBody Book book) {
        System.out.println(book);
        return bookService.update(book);
    }
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id) {
        return bookService.delete(id);
    }
    @GetMapping("{id}")
    public Book selectById(@PathVariable Integer id) {
        return bookService.selectById(id);
    }
    @GetMapping
    public List<Book> selectAll() {
        return bookService.selectAll();
    }
}
