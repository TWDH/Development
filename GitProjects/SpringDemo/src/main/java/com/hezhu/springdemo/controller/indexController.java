package com.hezhu.springdemo.controller;

import com.hezhu.springdemo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author He Zhu
 * @Date 2021-09-16 5:16 p.m.
 * @Version 1.0
 */
@RestController
@RequestMapping("/index")
public class indexController {

    @GetMapping
    public String getUser(User user) {
        String name = user.getFirstname() + " " + user.getLastname();
        System.out.println(name);
        return name;
    }
}
