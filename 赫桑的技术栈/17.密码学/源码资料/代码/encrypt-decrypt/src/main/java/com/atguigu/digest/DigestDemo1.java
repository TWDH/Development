package com.atguigu.digest;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * DigestDemo1
 *
 * @Author: 马伟奇
 * @CreateTime: 2020-05-05
 * @Description:
 *          消息摘要算法，为了防止篡改
 *          常见的加密算法：md5，sha1 ，sha256，sha512
 */
public class DigestDemo1 {
    public static void main(String[] args) throws Exception{
        //  原文
        // 4124bc0a9335c27f086f24ba207a4912
        // 4124bc0a9335c27f086f24ba207a4912
        // QSS8CpM1wn8IbyS6IHpJEg==
        String input = "aa";
        // 算法
//        String algorithm = "MD5";
//        String md5 = getDigest(input, algorithm);
//        System.out.println("md5===" + md5);
//
//        String sha1 = getDigest(input, "SHA-1");
//        System.out.println("sha1==="+sha1);
//
//        String sha256 = getDigest(input, "SHA-256");
//        System.out.println("sha256==="+sha256);
//
//        String sha512 = getDigest(input, "SHA-512");
//        System.out.println("sha512==="+sha512);
        //35876dd986890ddb5eb501f66a3f7c7d3fa6caa5
        //35876dd986890ddb5eb501f66a3f7c7d3fa6caa5
        String sha1 = getDigestFile("11.zip", "SHA-1");
        System.out.println(sha1);
        //dc3743e7bb409de25d1df26656cad1e15c206af17751f0d8ae5898c1325efac0c69918228649696f32126e4e32c99a73b422ef96b61cef75be384a4d0aef9c9f
        String sha512 = getDigestFile("11.zip", "SHA-512");
        System.out.println(sha512);


    }

    /**
     * 获取文件的数字摘要
     * @param filePath 表示文件的路径
     * @param algorithm 表示算法
     * @return
     */
    private static String getDigestFile(String filePath, String algorithm) throws Exception{
        // 通过io流的方式读取文件
        FileInputStream fis = new FileInputStream(filePath);
        // 定义长度
        int len ;
        // 定义一个字节数组
        byte[] buffer = new byte[1024];
        // 边读边写
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (((len = fis.read(buffer) )!= -1)){
            baos.write(buffer,0,len);
        }
        // 创建消息摘要对象
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        // 执行消息摘要算法
        byte[] digest1 = digest.digest(baos.toByteArray());

        return toHex(digest1);
    }

    /**
     * 获取数字摘要
     * @param input 原文
     * @param algorithm 算法
     * @return
     * @throws Exception
     */
    private static String getDigest(String input, String algorithm) throws Exception {
        // 创建消息摘要对象
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        // 执行消息摘要算法
        byte[] digest1 = digest.digest(input.getBytes());

        return toHex(digest1);
    }

    private static String toHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        // 对密文进行迭代
        for (byte b : digest) {
            // 把密文转换成16进制
            String s = Integer.toHexString(b & 0xff);
            // 判断如果密文的长度是1，需要在高位进行补0
            if (s.length() == 1){
                s = "0"+s;
            }
            sb.append(s);
//            System.out.print(s);
        }
        // 使用base64进行转码
        return sb.toString();
//        System.out.println(sb.toString());
    }
}