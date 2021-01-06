package jwtdemo.jwtdemo.web;


import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cyh
 * @date 2020/12/12 18:16
 */
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
