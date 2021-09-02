package com.ott.hezhu.hibernatejpa.controller;

import com.ott.hezhu.hibernatejpa.entity.cardType;
import com.ott.hezhu.hibernatejpa.entity.cardType2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author He Zhu
 * @date 2021-09-01 4:23 p.m.
 */

@RestController
public class indexController {

    @GetMapping("/index")
    public String index(String s1, String s2){
        System.out.println("S1: " + s1);
        System.out.println("S2: " + s2);

        return "Ok";
    }

    @GetMapping("/enum")
    public String enumIndex(cardType s1, cardType2 s2){
        System.out.println("S1: " + s1);
        System.out.println("S2: " + s2);

        return "Ok";
    }

}
