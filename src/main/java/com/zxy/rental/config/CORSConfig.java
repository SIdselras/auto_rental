package com.zxy.rental.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auther student_zxy
 * @date 2025/10/31
 * @project auto_rental
 */
@Configuration
public class CORSConfig implements WebMvcConfigurer {
        /**
     * 配置跨域资源共享(CORS)映射规则
     *
     * @param registry CORS注册器，用于添加跨域配置映射
     */
    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        // 配置全局跨域访问规则，允许所有来源、请求头和HTTP方法
        registry.addMapping("/**")
                //允许所有来源的跨域请求
                .allowedOriginPatterns("*")
                //允许所有请求头
                .allowedHeaders("*")
                //允许所有请求方法
                .allowedMethods("*")
                //允许请求携带认证信息 (如cookie)
                .allowCredentials(true)
                //跨域请求的缓存时间
                .maxAge(3600);
    }

}
