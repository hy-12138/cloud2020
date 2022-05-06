package com.hy.springCloud.controller;


import com.hy.springCloud.entities.CommonResult;
import com.hy.springCloud.entities.Payment;
import com.hy.springCloud.iRu.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/creat")
    public CommonResult consumerCreat(Payment payment){
        System.out.println(payment);
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create"
                ,payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult consumerGet(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id
                ,CommonResult.class);
    }

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancer loadBalancer;

    @GetMapping("/consumer/payment/lr")
    public Object lr(){
        //得到所有可用服务
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        //根据算法选出使用的服务
        ServiceInstance service = loadBalancer.service(instances);

        URI uri = service.getUri();

        return restTemplate.getForObject(uri+"/payment/lr",String.class);
    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }

}
