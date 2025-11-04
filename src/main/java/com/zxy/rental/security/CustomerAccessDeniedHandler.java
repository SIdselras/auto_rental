package com.zxy.rental.security;

import com.alibaba.fastjson2.JSON;
import com.zxy.rental.utils.Result;
import com.zxy.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @auther student_zxy
 * @date 2025/11/1
 * @project auto_rental
 */
@Component
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 处理访问拒绝异常，返回JSON格式的错误响应
     *
     * @param request HttpServletRequest对象，包含客户端请求信息
     * @param response HttpServletResponse对象，用于向客户端发送响应
     * @param accessDeniedException AccessDeniedException对象，访问拒绝异常信息
     * @throws IOException 当输入输出操作发生错误时抛出
     * @throws ServletException 当Servlet处理发生错误时抛出
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 设置响应内容类型为JSON格式
        response.setContentType("application/json");
        ServletOutputStream outputStream = response.getOutputStream();
        // 构造无权访问的错误响应结果
        String result = JSON.toJSONString(Result.fail().setCode(ResultCode.UNAUTHORIZED).setMessage("无权访问"));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

}
