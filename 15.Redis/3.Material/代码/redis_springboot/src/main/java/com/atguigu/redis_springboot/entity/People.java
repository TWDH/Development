package com.atguigu.redis_springboot.entity;

import java.io.Serializable;

/**
 * @Author He Zhu
 * @Date 2021-12-06 3:42 p.m.
 * @Version 1.0
 */

public class People implements Serializable {

    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
