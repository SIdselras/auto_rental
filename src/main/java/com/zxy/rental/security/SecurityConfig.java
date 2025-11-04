package com.zxy.rental.security;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @auther student_zxy
 * @date 2025/11/1
 * @project auto_rental
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailHandler loginFailHandler;
    @Resource
    private CustomerUserDetailsService customerUserDetailsService;
    @Resource
    private CustomerAnonymousEntryPoint customerAnonymousEntryPoint;
    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;
    @Resource
    private VerifyTokenFilter verifyTokenFilter;
    /**
     * 配置Spring Security的安全过滤器链
     *
     * @param http HttpSecurity对象，用于配置安全策略
     * @return SecurityFilterChain 安全过滤器链配置
     * @throws Exception 配置过程中可能抛出的异常
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 配置表单登录相关设置
        // 登录前过滤配置
        http.addFilterBefore(verifyTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin()
                .loginProcessingUrl("/rental/user/login") // 设置登录处理URL
                .successHandler(loginSuccessHandler) // 设置登录成功处理器
                .failureHandler(loginFailHandler) // 设置登录失败处理器
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 设置会话创建策略为无状态
                .and()
                .authorizeHttpRequests() // 授权请求配置
                .requestMatchers("/rental/user/login") // 匹配登录请求
                .permitAll() // 允许所有请求访问
                .anyRequest().authenticated() // 任何其他请求需要认证
                .and()
                .exceptionHandling() // 异常处理配置
                .authenticationEntryPoint(customerAnonymousEntryPoint) // 设置未认证入口点
                .accessDeniedHandler(customerAccessDeniedHandler) // 设置访问拒绝处理器
                .and()
                .cors() // 跨域配置
                .and()
                .csrf().disable() // 关闭CSRF保护  跨站请求伪造 是一种网络攻击
                .userDetailsService(customerUserDetailsService); // 设置用户详情服务
        return http.build();//构建并返回安全过滤连
    }
}
