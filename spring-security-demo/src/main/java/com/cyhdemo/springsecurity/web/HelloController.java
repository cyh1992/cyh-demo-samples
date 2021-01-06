package com.cyhdemo.springsecurity.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyh
 * @date 2020/12/4 18:13
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return"hello";
    }

    @GetMapping("/success")
    public String success() {
        return"success";
    }


}
