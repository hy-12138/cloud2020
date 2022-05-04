package com.hy.springCloud.service;

import com.hy.springCloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    //插入数据
    public int create(Payment payment);
    //根据id查询数据
    public Payment selectById(Integer id);
}
