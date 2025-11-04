package com.zxy.rental.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther student_zxy
 * @date 2025/10/31
 * @project auto_rental
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
        /**
     * 创建MybatisPlus拦截器配置
     *
     * @return MybatisPlusInterceptor 配置好的MybatisPlus拦截器实例
     */
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 创建MybatisPlus拦截器实例
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页拦截器，指定数据库类型为MYSQL
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
