package com.zxy.rental.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @auther student_zxy
 * @date 2025/11/1
 * @project auto_rental
 */
public class CustomerAuthenticationException extends AuthenticationException {
    public CustomerAuthenticationException(String msg) {
        super(msg);
    }
}
