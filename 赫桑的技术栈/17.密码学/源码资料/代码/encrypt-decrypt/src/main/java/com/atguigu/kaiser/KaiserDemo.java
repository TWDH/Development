package com.atguigu.kaiser;

/**
 * KaiserDemo
 *
 * @Author: 马伟奇
 * @CreateTime: 2020-05-05
 * @Description:
 *       凯撒加密
 */
public class KaiserDemo {
    public static void main(String[] args) {
        // 定义原文
        String input = "Hello World";
        // 把原文右边移动3位
        int key = 3;
        // 凯撒加密
        String s = encrypt(input,key);
        System.out.println("加密==" + s);
        String s1 = decrypt(s,key);
        System.out.println("明文=="+s1);
    }

    /**
     * 解密
     * @param s 密文
     * @param key 密钥
     * @return
     */
    public static String decrypt(String s, int key) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            // 偏移数据
            b -= key;
            char newb = (char) b;
            sb.append(newb);
        }
        return sb.toString();
    }

    /**
     * 加密
     * @param input 原文
     * @return
     */
    public static String encrypt(String input,int key) {
        // 抽取快捷键 ctrl + alt + m
        // 把字符串变成字节数组
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            // 往右边移动3位
            b = b + key;
            char newb = (char) b;
            sb.append(newb);
        }
//        System.out.println("密文==="+sb.toString());
        return sb.toString();
    }
}