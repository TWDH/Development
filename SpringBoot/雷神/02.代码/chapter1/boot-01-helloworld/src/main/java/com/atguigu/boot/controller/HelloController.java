package com.atguigu.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// @ResponseBody //字符直接在页面书写，而不是跳转到某个浏览器
// @Controller
@RestController
@Slf4j
public class HelloController {


    @RequestMapping("/hello")
    public String handle01(@RequestParam(name = "name") String name) {
        log.info("请求进来了...");
        return "Hello, Spring Boot 2!"+"你好："+name;
    }
}
