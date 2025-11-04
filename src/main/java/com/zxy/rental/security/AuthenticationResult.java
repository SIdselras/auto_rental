package com.zxy.rental.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther student_zxy
 * @date 2025/11/1
 * @project auto_rental
 */
/**
 * 认证登录处理结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResult {
    private int id;
    private int code;
    private String token;
    private Long expireTime;
}
