package com.zxy.rental.service;

import com.zxy.rental.entity.User;
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
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 根据用户id查询用户角色名称
     * @param userId
     * @return
     */
    List<String> selectRoleNameByUserId(Integer userId);
}
