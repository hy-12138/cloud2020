package com.hy.springCloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    String configInfo;

    @GetMapping("/client/config")
    public String getConfigInfo(){
        return configInfo;
    }
}
