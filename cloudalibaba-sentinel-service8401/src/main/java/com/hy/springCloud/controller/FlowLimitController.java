package com.hy.springCloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hy.springCloud.service.OrderServiceImpl3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class FlowLimitController {


    @GetMapping("/testHotKey")
    /**
     * value:表示资源名称，blockHandler：表示兜底方法的名称
     */
    @SentinelResource(value = "testHotKey",blockHandler = "block_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "------------testHotKey";
    }
    public String block_testHotKey(String p1, String p2, BlockException e){
        return "block_testHotKey-----o(╥﹏╥)o";
    }

    @GetMapping("/testA")
    @SentinelResource(value = "testA")
    public String testA()
    {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        return "------testB";
    }

    @GetMapping("/commonA")
    public void commonA(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/testA").forward(req,resp);
    }

    @GetMapping("/testD")
    public String testD()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "------testD";
    }


    @GetMapping("/testC")
    public String testC()
    {
        int a = 10 / 0 ;
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE()
    {
        int a = 10 / 0 ;
        return "------testE";
    }

    @Autowired
    private OrderServiceImpl3 orderServiceImpl3;

    @RequestMapping("/order/message1")
    public String message1() {
        this.orderServiceImpl3.dosomething();
        return "message1";
    }

    @RequestMapping("/order/message2")
    public String message2() {
        this.orderServiceImpl3.dosomething();
        return "message2";
    }
}
