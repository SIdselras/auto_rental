package com.zxy.rental.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @auther student_zxy
 * @date 2025/10/31
 * @project auto_rental
 */
/**
 * JWT工具类，用于创建和解析JWT令牌
 */
@Component
public class JwtUtils {
    /**
     * 密钥字符串，用于签名和验证JWT令牌
     */
    public static final String SECRET_KEY = "student_zxy";

    /**
     * 令牌过期时间，单位毫秒（30分钟）
     */
    public static final long EXPIRATION_TIME = 1000L*60*30;

    /**
     * 创建JWT令牌
     * @param payload 包含用户信息的载荷数据
     * @return 生成的JWT令牌字符串
     */
    public static String createToken(Map<String, Object> payload) {
        DateTime now = DateTime.now();
        DateTime newTime = new DateTime(now.getTime()
        + EXPIRATION_TIME);
        // 设置JWT标准声明：签发时间、过期时间、生效时间
        payload.put(JWTPayload.ISSUED_AT, now);
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        payload.put(JWTPayload.NOT_BEFORE, now);
        return JWTUtil.createToken(payload, SECRET_KEY.getBytes());
    }

    /**
     * 解析并验证JWT令牌
     * @param token 待解析的JWT令牌字符串
     * @return 解析后的JWT载荷对象
     * @throws RuntimeException 当令牌验证失败或已过期时抛出异常
     */
    public static JWTPayload parseToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        // 验证令牌签名
        if(!jwt.setKey(SECRET_KEY.getBytes()).verify()){
            throw new RuntimeException("token异常");
        }
        // 验证令牌是否过期
        if(!jwt.validate(0)){
            throw new RuntimeException("token已过期");
        }
        return jwt.getPayload();
    }
}

