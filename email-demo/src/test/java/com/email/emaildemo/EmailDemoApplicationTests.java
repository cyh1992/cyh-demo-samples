package com.email.emaildemo;

import com.email.emaildemo.pojo.Book;
import com.email.emaildemo.service.MailService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class EmailDemoApplicationTests {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;
    @Test
    void contextLoads() {

        Context context = new Context();
        context.setVariable("subject", "图书清册");
        List<Book> books = Lists.newArrayList();
        books.add(new Book("1", "Go 语言基础", "张三", new BigDecimal(100)));
        books.add(new Book("2", "Go 语言实战", "李四", new BigDecimal(100)));
        books.add(new Book("2", "Go 语言进阶", "王五", new BigDecimal(100)));
        Map<String,Object> map = new HashMap<>();
        map.put("total",100);
        map.put("sales",888.9);
        map.put("user",20);
        context.setVariable("books", books);
//        context.setVariable("total",100);
//        context.setVariable("sales",888.9);
        context.setVariables(map);
        String mail = templateEngine.process("mailtemplate.html", context);
        mailService.sendHtmlMail("503712395@qq.com", "1090064649@qq.com", "图书清册",mail);

    }

}
