package com.zxy.rental.security;

import com.alibaba.fastjson.JSON;
import com.zxy.rental.utils.Result;
import com.zxy.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @auther student_zxy
 * @date 2025/11/1
 * @project auto_rental
 */
@Component
public class CustomerAnonymousEntryPoint implements AuthenticationEntryPoint {

    /**
     * 当用户未认证时执行的处理方法
     *
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param authException 认证异常信息
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 设置响应内容类型为JSON格式
        response.setContentType("application/json");
        ServletOutputStream outputStream = response.getOutputStream();

        // 构造未授权访问的错误响应结果
        String result = JSON.toJSONString(Result.fail().setCode(ResultCode.UNAUTHORIZED).setMessage("匿名用户无法访问"));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

}
