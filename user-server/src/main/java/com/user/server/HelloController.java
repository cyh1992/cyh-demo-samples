package com.user.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyh
 * @date 2021/1/22 13:25
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping("/admin/hello")
    public String admin() {
        return "admin";
    }
}
