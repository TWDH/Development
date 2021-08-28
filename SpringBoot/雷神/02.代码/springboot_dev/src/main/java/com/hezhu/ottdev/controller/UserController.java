package com.hezhu.ottdev.controller;

import com.hezhu.ottdev.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/user/index")
    public String userIndex() {
        return "This is userIndex";
    }

    @GetMapping("/center")
    public String center() {
        return "This is center";
    }

    // 登录 interceptor
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        User user = new User();
        user.setId(1);
        user.setUsername("hezhu");

        request.getSession().setAttribute("user", user);
        return "Login Success";
    }

    @GetMapping("/withoutLogin")
    public String withoutLogin() {
        return "without Login auth";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
