package com.hezhu.security.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class securityController {

    @GetMapping("hello")
    public String testController() {
        return "hello world";
    }
}
