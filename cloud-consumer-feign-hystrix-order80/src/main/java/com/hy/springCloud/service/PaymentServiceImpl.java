package com.hy.springCloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK，网络异常...o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "paymentInfo_TimeOut，网络异常...o(╥﹏╥)o";
    }
}
