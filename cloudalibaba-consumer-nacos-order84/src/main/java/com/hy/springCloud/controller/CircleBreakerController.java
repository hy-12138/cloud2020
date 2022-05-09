package com.hy.springCloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hy.springCloud.entities.CommonResult;
import com.hy.springCloud.entities.Payment;
import com.hy.springCloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String serviceURL;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",blockHandler = "blockHandler",fallback = "fallbackHandler")
    public CommonResult<Payment> fallback(@PathVariable("id") long id){
        CommonResult<Payment> result = restTemplate.getForObject(serviceURL + "/paymentSQL/"+id, CommonResult.class, id);

        if(id == 4){
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if(result == null){
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return result;
    }

    public CommonResult<Payment> blockHandler(long id, BlockException blockException){
        Payment payment = new Payment(id, null);
        return new CommonResult<Payment>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }

    public CommonResult<Payment> fallbackHandler(long id ,Throwable e){
        Payment payment = new Payment(id, null);
        return new CommonResult<Payment>(445,"fallback,无此流水,exception  "+e.getMessage(),payment);
    }


    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/openfeign/{id}")
    public CommonResult<Payment> openfeign(@PathVariable("id") long id){
        if(id == 4)
        {
            throw new RuntimeException("没有该id");
        }
        return paymentService.paymentSQL(id);
    }
}
