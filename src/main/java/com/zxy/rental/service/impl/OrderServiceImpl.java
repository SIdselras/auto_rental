package com.zxy.rental.service.impl;

import com.zxy.rental.entity.Order;
import com.zxy.rental.mapper.OrderMapper;
import com.zxy.rental.service.IOrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
