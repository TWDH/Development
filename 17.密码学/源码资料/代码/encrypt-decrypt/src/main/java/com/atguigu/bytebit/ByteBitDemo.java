package com.atguigu.bytebit;

/**
 * ByteBitDemo
 *
 * @Author: 马伟奇
 * @CreateTime: 2020-05-05
 * @Description:
 */
public class ByteBitDemo {
    /**
     * 根据编码的格式不一样，对应的字节也不一样
     * 如果是UTF-8:一个中文对应的是三个字节
     * 如果是GBK : 一个中文对应的是二个字节
     *
     * 如果是英文，就无所谓编码格式
     */
    public static void main(String[] args) throws Exception{
        String a = "A";
        byte[] bytes = a.getBytes();
        for (byte aByte : bytes) {
            System.out.println(aByte);
            String s = Integer.toBinaryString(aByte);
            System.out.println(s);
        }
    }
}