package com.zxy.rental.security;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @auther student_zxy
 * @date 2025/11/1
 * @project auto_rental
 */
/**
 * CustomerPasswordEncoder类用于处理密码编码和匹配操作
 * 该类实现了PasswordEncoder接口，提供自定义的密码处理逻辑
 */
@Component
public class CustomerPasswordEncoder implements PasswordEncoder {
    /**
     * 对原始密码进行编码处理
     * @param rawPassword 原始密码字符序列
     * @return 编码后的密码字符串
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    /**
     * 验证原始密码与已编码密码是否匹配
     * @param rawPassword 原始密码字符序列
     * @param encodedPassword 已编码的密码字符串
     * @return 如果密码匹配返回true，否则返回false
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // 使用字符串工具类比较编码后的密码与提供的编码密码是否相等
        System.out.println(StrUtil.equals(encode(rawPassword), encodedPassword));
        return StrUtil.equals(encode(rawPassword), encodedPassword);
    }
}

