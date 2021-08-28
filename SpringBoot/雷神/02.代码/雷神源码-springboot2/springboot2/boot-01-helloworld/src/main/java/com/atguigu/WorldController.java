package com.atguigu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorldController {

    @RequestMapping("/w")
    public String world66(){
        return "World";
    }
}
