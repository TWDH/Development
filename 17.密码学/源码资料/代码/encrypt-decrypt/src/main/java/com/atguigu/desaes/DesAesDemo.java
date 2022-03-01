package com.atguigu.desaes;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DesAesDemo
 *
 * @Author: 马伟奇
 * @CreateTime: 2020-05-05
 * @Description:
 *         对称加密
 */
public class DesAesDemo {
    public static void main(String[] args) throws Exception{
        // 原文 如果使用的是不填充的模式，那么原文必须是8个字节的整数倍
        String input = "硅谷12";
        // 定义key
        // 如果使用des进行加密，那么密钥必须是8个字节
        String key = "12345678";
        // 算法 qANksk5lvqM=
//        String transformation = "DES";
        // ECB:表示加密模式
        // PKCS5Padding:表示填充模式 qANksk5lvqM=
//        String transformation = "DES/ECB/PKCS5Padding";
        // 如果默认情况，没有写填充模式和加密模式，那么默认就使用DES/ECB/PKCS5Padding
//        8Ze/OtPlSaU=
//        String transformation = "DES/CBC/PKCS5Padding";
        //Y6htKI/ceJg=
        String transformation = "DES/CBC/NoPadding";
        // 加密类型
        String algorithm = "DES";
        // 指定获取密钥的算法
        String encryptDES = encryptDES(input, key, transformation, algorithm);
        System.out.println("加密:" + encryptDES);

        String s = decryptDES(encryptDES, key, transformation, algorithm);
        System.out.println("解密:" + s);
    }

    /**
     * 解密
     * @param encryptDES  密文
     * @param key         密钥
     * @param transformation 加密算法
     * @param algorithm   加密类型
     * @return
     */
    private static String decryptDES(String encryptDES, String key, String transformation, String algorithm) throws Exception{
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),algorithm);
        // 创建iv向量
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        //Cipher.DECRYPT_MODE:表示解密
        // 解密规则
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,iv);
        // 解密，传入密文
        byte[] bytes = cipher.doFinal(Base64.decode(encryptDES));

        return new String(bytes);
    }

    /**
     * 使用DES加密数据
     *
     * @param input          : 原文
     * @param key            : 密钥(DES,密钥的长度必须是8个字节)
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @return : 密文
     * @throws Exception
     */
    private static String encryptDES(String input, String key, String transformation, String algorithm) throws Exception {
        // 获取加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则
        // 第一个参数key的字节
        // 第二个参数表示加密算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 创建iv向量，iv向量，是使用到CBC加密模式
        // 在使用iv向量进行加密的时候，iv的字节也必须是8个字节
        IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
        // ENCRYPT_MODE：加密模式
        // DECRYPT_MODE: 解密模式
        // 初始化加密模式和算法
        cipher.init(Cipher.ENCRYPT_MODE,sks,iv);
        // 加密
        byte[] bytes = cipher.doFinal(input.getBytes());

        // 输出加密后的数据
        String encode = Base64.encode(bytes);

        return encode;
    }
}