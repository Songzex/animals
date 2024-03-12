package com.scy.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtils {
    @Value("${Animal.jwt.secret}")
    private String secret;  //取出配置文件的密码值

    @Value("${Animal.jwt.expire}") //设置token的过期时间
    private int expire;

    public  String createToken( Integer userId){
        Date date= DateUtil.offset(new Date(), DateField.DAY_OF_YEAR,5);
        Algorithm algorithm=Algorithm.HMAC256(secret);
        JWTCreator.Builder builder= JWT.create();
        String token=builder.withClaim("userId",userId).withExpiresAt(date).sign(algorithm);
        return token;
    }

    public int getUserId(String token){
        try {
            // 创建一个 JWT 解码器并使用密钥初始化
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            // 使用 JWT 解码器验证 Token
            DecodedJWT jwt = verifier.verify(token);
            // 获取 Token 中的 userId 声明并返回其整数值
            return jwt.getClaim("userId").asInt();//null
        } catch (JWTDecodeException e) {
            // 如果解码失败，可能是因为 Token 格式错误或缺少 userId 声明
            // 在此处处理异常，例如记录日志或返回默认值
            e.printStackTrace();
            return -1; // 或者抛出自定义异常
        }
    }

    public boolean verifierToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true; // 令牌验证成功
        } catch (JWTVerificationException e) {
            // 令牌验证失败
            return false;
        }
    }





}
