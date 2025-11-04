package com.zxy.rental.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zxy.rental.entity.User;
import com.zxy.rental.utils.JwtUtils;
import com.zxy.rental.utils.RedisUtils;
import com.zxy.rental.utils.ResultCode;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther student_zxy
 * @date 2025/11/1
 * @project auto_rental
 */

/**
 * 登录成功处理器，实现Spring Security的认证成功回调接口。
 * 在用户认证成功后生成JWT Token，并将结果以JSON形式返回给前端，
 * 同时将Token存储到Redis中以便后续使用。
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private RedisUtils redisUtils;

    /**
     * 认证成功后的处理逻辑。
     * 当用户成功认证后，该方法会被调用，用于生成并返回JSON格式的认证结果，包括token等信息。
     *
     * @param request        HttpServletRequest对象，代表客户端的请求。
     * @param response       HttpServletResponse对象，用于向客户端发送响应。
     * @param authentication 认证信息，包含用户详细信息。
     * @throws IOException      如果发生输入/输出错误。
     * @throws ServletException 如果发生与Servlet相关的异常。
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 设置响应内容类型为JSON
        response.setContentType("application/json;charset=utf-8");

        // 从认证信息中提取用户对象
        User user = (User) authentication.getPrincipal();

        // 准备token相关数据并生成token
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("userid", user.getId());
        String token = JwtUtils.createToken(map);

        // 解析token获取过期时间，并构建认证结果对象
        NumberWithFormat claim = (NumberWithFormat) JwtUtils.parseToken(token).getClaim(JWTPayload.EXPIRES_AT);
        long expireTime = Convert.toDate(claim).getTime();
        AuthenticationResult authenticationResult
                = new AuthenticationResult(user.getId(),
                ResultCode.SUCCESS, token, expireTime);

        // 将认证结果转换为JSON字符串
        String result = JSON.toJSONString(authenticationResult,
                SerializerFeature.DisableCircularReferenceDetect);

        // 向客户端发送认证结果
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        // 将token存入Redis，设置过期时间
        String tokenKey = "token:" + token;
        long nowTime = DateTime.now().getTime();
        redisUtils.set(tokenKey, token, (expireTime - nowTime) / 1000);
    }
}
