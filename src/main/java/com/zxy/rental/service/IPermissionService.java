package com.zxy.rental.service;

import com.zxy.rental.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author student_zxy
 * @since 2025-10-31
 */
public interface IPermissionService extends IService<Permission> {
    List<Permission> selectPermissionByUserId(Integer UserId);
}
