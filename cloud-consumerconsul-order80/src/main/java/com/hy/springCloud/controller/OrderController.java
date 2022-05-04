package com.hy.springCloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://consul-provider-payment";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public Object consul(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
    }
}
