package com.hy.springCloud.service;

import com.hy.springCloud.entities.CommonResult;
import com.hy.springCloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentService {

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> selectById(@PathVariable("id") Integer id);

    @GetMapping("/payment/feignTimeOut")
    public String feignTimeOut();
}
