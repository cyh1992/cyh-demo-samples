package com.securitydemo2.securitydemo2.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyh
 * @date 2020/12/12 22:24
 */
@RestController
public class LoginController {

    @GetMapping("/hello")
    public String hello() {
        return"hello";
    }

    @GetMapping("/success")
    public String success() {
        return"登录成功";
    }

    @GetMapping("/fail")
    public String fail() {
        return"登录失败";
    }

    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("123");
        System.out.println(encode);
    }
}
