package com.kevin.zhu.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("index")
public class indexController {
    @GetMapping()
    public String hello() {

        //如果没有@ResponseBody, 跳转到hello页面
        return "hello";
    }
}
