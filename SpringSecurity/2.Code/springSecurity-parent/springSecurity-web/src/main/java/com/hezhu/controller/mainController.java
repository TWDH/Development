package com.hezhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {

    @RequestMapping({"/", "index", ""})
    public String mainIndex() {

        // resources/templates/index.html
        return "index";
    }

}
