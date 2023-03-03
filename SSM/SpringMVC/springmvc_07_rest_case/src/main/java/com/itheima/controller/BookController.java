package com.itheima.controller;

import com.itheima.domain.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    //保存的功能
    @PostMapping
    public String saveBooks(@RequestBody Book book) {
        System.out.println("新书籍存入成功" + book);
        return "save successful";
    }
    //获取的功能
    @GetMapping
    public List<Book> getBooks() {
        System.out.println("获取所有的书本");
        Book book1 = new Book();
        Book book2 = new Book();
        book1.setName("海上灵光");
        book1.setId(111);
        book1.setDescription("作者许嵩");
        book1.setType("文学");

        book2.setName("孙子兵法");
        book2.setId(222);
        book2.setDescription("作者孙子");
        book2.setType("历史");
        List<Book> books = new ArrayList<Book>();

        books.add(book1);
        books.add(book2);

        return books;
    }
}
