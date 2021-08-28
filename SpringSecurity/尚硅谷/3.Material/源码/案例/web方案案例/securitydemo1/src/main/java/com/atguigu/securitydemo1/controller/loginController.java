package com.atguigu.securitydemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginController {
    @GetMapping("csrf")
    public String csrf(Model model) {
        return "csrf/csrfTest";
    }

    @GetMapping("userLogin")
    public String login2(Model model) {
        return "login2";
    }

    @PostMapping("/toupdate")
    public String test(Model model){
        return "csrf/csrfTest";
    }

    @PostMapping("/update_token")
    public String getToken() {
        return "csrf/csrf_token";
    }
}
