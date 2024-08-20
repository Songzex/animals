package com.scy.utils;

import jdk.nashorn.internal.ir.IfNode;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    // 加密算法
    private static final String ALGORITHM_NAME = "MD5";
    // 哈希迭代次数
    private static final int HASH_ITERATIONS = 1;

    // 对密码进行加密，并返回加密后的密码字符串
    public static String encryptPassword(String password, String salt) {
        // 使用 MD5 算法进行密码加密
        return new SimpleHash(ALGORITHM_NAME, password, ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
    }

    // 校验密码是否正确
    public static boolean verifyPassword(String inputPassword, String salt, String encryptedPassword) {
        // 对用户输入的密码进行加密
        String encryptedInputPassword = encryptPassword(inputPassword, salt);
        // 比对加密后的密码与数据库中存储的密码是否一致
        return encryptedInputPassword.equals(encryptedPassword);
    }
    //获取密码明文
    public static String decryptPassword(String encryptedPassword, String salt) {
        // 因为 MD5 是不可逆的加密算法，无法从密文还原明文密码
        // 所以在这个方法中，我们只能返回加密后的密码
        return encryptedPassword;
    }



}
