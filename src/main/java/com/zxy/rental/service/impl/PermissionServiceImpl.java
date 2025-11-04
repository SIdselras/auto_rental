package com.zxy.rental.service.impl;

import com.zxy.rental.entity.Permission;
import com.zxy.rental.mapper.PermissionMapper;
import com.zxy.rental.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Permission> selectPermissionByUserId(Integer userId) {
        return baseMapper.selectPermissionListByUserId(userId);
    }
}
