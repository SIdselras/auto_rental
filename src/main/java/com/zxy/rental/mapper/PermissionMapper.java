package com.zxy.rental.mapper;

import com.zxy.rental.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author student_zxy
 * @since 2025-10-31
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectPermissionListByUserId(Integer userId);
}
