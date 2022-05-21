package com.atguigu.ascii;

/**
 * AsciiDemo
 *
 * @Author: 马伟奇
 * @CreateTime: 2020-05-05
 * @Description:
 */
public class AsciiDemo {

    public static void main(String[] args) {
//        char a = 'a';
//        int b = a;
        // 定义字符串
        String a = "AaZ";
        // 需要拆开字符串
        char[] chars = a.toCharArray();
        for (char aChar : chars) {
            int asciicode = aChar;
            System.out.println(asciicode);
        }
        // 打印b，在zascii当中十进制的数字对应是多少
//        System.out.println(b);
    }
}