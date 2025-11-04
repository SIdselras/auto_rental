package com.zxy.rental.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @auther student_zxy
 * @date 2025/10/31
 * @project auto_rental
 */
/**
 * Redis工具类，提供对Redis的基本操作封装
 */
@Component
public class RedisUtils {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置Redis键值对
     * @param key 键
     * @param value 值
     * @param timeout 过期时间（秒）
     */
    public void set(String key, String value, Long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);

    }

    /**
     * 根据键获取Redis中的值
     * @param key 键
     * @return 返回对应的值，如果不存在则返回null
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除Redis中的键值对
     * @param key 要删除的键
     */
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }
}

