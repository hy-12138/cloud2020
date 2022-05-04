package com.hy.springCloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    //服务端地址
    public static final String PROVIDER_HOST = "http://cloud-provider-payment";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer/order")
    public Object order(){
        return restTemplate.getForObject(PROVIDER_HOST+"/payment/zk",String.class);
    }
}
