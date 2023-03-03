package com.itheima.controller;


import com.itheima.domain.Book;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping
    public Result save(@RequestBody Book book) {
        Boolean flag = bookService.save(book);
        return new Result(flag ? Code.SAVE_ok : Code.SAVE_ERR, flag);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        Boolean flag = bookService.update(book);
        return new Result(flag ? Code.UPDATE_ok : Code.UPDATE_ERR, flag);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id) {
        Boolean flag = bookService.delete(id);
        return new Result(flag ? Code.DELETE_ok : Code.DELETE_ERR, flag);
    }

    @GetMapping("{id}")
    public Result selectById(@PathVariable Integer id) {
        Book book = bookService.selectById(id);
        Integer code = book != null ? Code.SAVE_ok :Code.SAVE_ERR;
        String msg = book != null ? "数据查询成功！" : "数据查询失败，请重试！";
        return new Result(code,book,msg);
    }

    @GetMapping
    public Result selectAll() {
        List<Book> bookList = bookService.selectAll();
        Integer code = bookList != null ? Code.SAVE_ok :Code.SAVE_ERR;
        String msg = bookList != null ? "数据查询成功！" : "数据查询失败，请重试！";
        return new Result(code,bookList,msg);
    }
}
