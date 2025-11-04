package com.zxy.rental.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @auther student_zxy
 * @date 2025/10/31
 * @project auto_rental
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
        /**
     * 插入数据时自动填充创建时间和更新时间字段
     *
     * @param metaObject 元数据对象，包含实体类的字段信息
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 填充创建时间字段，使用当前时间
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        // 填充更新时间字段，使用当前时间
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }


        /**
     * 更新记录时自动填充更新时间字段
     *
     * @param metaObject 元数据对象，包含实体类的字段信息
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 严格填充更新时间字段，使用当前时间作为值
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }

}
