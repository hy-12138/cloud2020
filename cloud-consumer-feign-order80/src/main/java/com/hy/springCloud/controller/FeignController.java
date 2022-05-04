package com.hy.springCloud.controller;

import com.hy.springCloud.entities.CommonResult;
import com.hy.springCloud.entities.Payment;
import com.hy.springCloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FeignController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") int id){
        return paymentService.selectById(id);
    }


    @GetMapping("/consumer/payment/feignTimeOut")
    public String feignTimeOut(){
        return paymentService.feignTimeOut();
    }
}
