package com.hezhu.security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomLoginController {
    @RequestMapping("/login/page")
    public String toLogin() {
        // resources/templates/login.html
        return "login";
    }
}
