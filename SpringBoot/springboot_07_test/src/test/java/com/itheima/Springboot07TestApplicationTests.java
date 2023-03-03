package com.itheima;

import com.itheima.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//如果引导类在测试类所在的包或者子包下，则会自动注入引导类的测试环境
@SpringBootTest
class Springboot07TestApplicationTests {

    //注入资源
    @Autowired
    private BookService bookService;

    @Test
    void testSave() {
        bookService.save();
    }
}
