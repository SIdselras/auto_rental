package com.zxy.rental.service.impl;

import com.zxy.rental.entity.Customer;
import com.zxy.rental.mapper.CustomerMapper;
import com.zxy.rental.service.ICustomerService;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
