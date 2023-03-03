package com.itheima1;

import com.itheima.Springboot07TestApplication;
import com.itheima.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


//如果引导类不在测试类所在的包或者子包下，则需要指定引导类
@SpringBootTest(classes = Springboot07TestApplication.class)
class Springboot07TestApplicationTests {

    @Autowired
    private BookService bookService;

    @Test
    void testSave() {
        bookService.save();
    }
}
