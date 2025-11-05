package com.zxy.rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxy.rental.entity.AutoMaker;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author student_zxy
 * @since 2025-10-31
 */
public interface IAutoMakerService extends IService<AutoMaker> {
    Page<AutoMaker> search(int start, int size, AutoMaker autoMaker);
}
