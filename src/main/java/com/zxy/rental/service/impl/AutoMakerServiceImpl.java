package com.zxy.rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxy.rental.entity.AutoMaker;
import com.zxy.rental.mapper.AutoMakerMapper;
import com.zxy.rental.service.IAutoMakerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author student_zxy
 * @since 2025-10-31
 */
@Service
public class AutoMakerServiceImpl extends ServiceImpl<AutoMakerMapper, AutoMaker> implements IAutoMakerService {

    /**
     * 搜索汽车制造商列表
     * @param start 分页起始位置
     * @param size 每页大小
     * @param autoMaker 搜索条件对象，包含制造商名称等查询条件
     * @return 分页结果，包含符合条件的汽车制造商列表
     */
    @Override
    public Page<AutoMaker> search(int start, int size, AutoMaker autoMaker) {
        // 构建查询条件
        // 创建查询条件对象
        QueryWrapper<AutoMaker> queryWrapper = new QueryWrapper<>();
        // 添加排序条件，按排序字母升序排序
        // 如果输入了制造商名称，则按名称进行模糊查询，where条件
        queryWrapper.orderByAsc("order_letter")
                .like(StrUtil.isNotEmpty(autoMaker.getName()),"name",
                        autoMaker.getName());
        // 加载分页器
        Page<AutoMaker> page = new Page<>(start, size);
        // 生成分页结果
        this.page(page, queryWrapper);
        return page;
    }

}
