package com.hy.springCloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl3 {
 
    @SentinelResource("dosomething")       // 将此方法标注为sentinel的资源。value=资源名
    public void dosomething() {
        System.out.println("do something");
    }
 
}