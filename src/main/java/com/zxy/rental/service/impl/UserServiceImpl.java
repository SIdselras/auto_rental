package com.zxy.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxy.rental.entity.User;
import com.zxy.rental.mapper.UserMapper;
import com.zxy.rental.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author student_zxy
 * @since 2025-10-31
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

        /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 返回匹配的用户对象，如果不存在则返回null
     */
    @Override
    public User selectByUsername(String username) {
        // 构造查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        // 执行查询并返回结果
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据用户id查询用户角色名称
     * @param userId
     * @return
     */
    @Override
    public List<String> selectRoleNameByUserId(Integer userId) {
        //构造查询条件
        return baseMapper.selectRoleNameByUserId(userId);
    }


}
