package com.hy.springCloud.controller;

import com.hy.springCloud.entities.CommonResult;
import com.hy.springCloud.entities.Payment;
import com.hy.springCloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.io.ObjectInputStream;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    String serverPort;

    @Resource
    DiscoveryClient discoveryClient;

    //插入数据
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if(result > 0){
            return new CommonResult(200,"插入成功,serverPort:"+serverPort,result);
        }else{
            return new CommonResult(501,"插入失败");
        }
    }

    //根据id查询数据
    @GetMapping("/payment/get/{id}")
    public CommonResult selectById(@PathVariable("id") Integer id){
        Payment payment = paymentService.selectById(id);
        if(payment != null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(501,"查询失败，id："+id+"不存在");
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        System.out.println(services);

        List<ServiceInstance> instances = discoveryClient
                .getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping("/payment/lr")
    public String lr(){
        return serverPort;
    }


    @GetMapping("/payment/feignTimeOut")
    public String feignTimeOut(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
