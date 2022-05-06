package com.hy.springCloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    @Value("${service-url.nacos-user-service}")
    private String serviceURL;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String getConsumer(@PathVariable("id") long id){
        return restTemplate.getForObject(serviceURL+"/payment/nacos/"+id,String.class);
    }
}
