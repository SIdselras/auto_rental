package com.zxy.rental.security;

import com.alibaba.fastjson.JSON;
import com.zxy.rental.utils.Result;
import com.zxy.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @auther student_zxy
 * @date 2025/11/1
 * @project auto_rental
 */
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

    /**
     * 处理认证失败的情况
     *
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param exception 认证异常对象
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();

        // 根据不同的认证异常类型设置相应的错误码和错误信息
        int code = ResultCode.ERROR;
        String msg = null;

        if(exception instanceof AccountExpiredException){
            code = ResultCode.UNAUTHENTICATED;
            msg = "账户过期";
        }else if(exception instanceof BadCredentialsException){
            code = ResultCode.UNAUTHORIZED;
            msg = "用户名或密码错误";
        }else if(exception instanceof CredentialsExpiredException){
            code = ResultCode.UNAUTHORIZED;
            msg = "密码过期";
        }else if(exception instanceof DisabledException){
            code = ResultCode.UNAUTHORIZED;
            msg = "账户被禁用";
        }else if(exception instanceof LockedException){
            code = ResultCode.UNAUTHORIZED;
            msg = "账户被锁顶";
        }else if(exception instanceof InternalAuthenticationServiceException){
            code = ResultCode.UNAUTHORIZED;
            msg = "用户不存在";
        }else if(exception instanceof CustomerAuthenticationException){
            code = ResultCode.UNAUTHORIZED;
            msg = exception.getMessage();
        }else {
            msg = "登录失败";
        }

        // 将错误信息转换为JSON格式并写入响应流
        String result = JSON.toJSONString(Result.fail().setCode(code).setMessage(msg));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

}
