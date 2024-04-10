package com.bondsales.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    public static String encodePassword(String password, String salt) {
        try {
            // 创建MessageDigest对象，指定算法为MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 将密码和盐值拼接成新的字符串
            String passwordWithSalt = password + salt;
            // 将拼接后的字符串转换为字节数组
            byte[] passwordBytes = passwordWithSalt.getBytes();
            // 对密码进行加密
            byte[] encryptedBytes = md.digest(passwordBytes);
            // 将加密后的字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : encryptedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // 处理异常
            return null;
        }
    }
}
