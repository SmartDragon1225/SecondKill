package com.tian.secondkill.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.tian.secondkill.service.OrderSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;

public class RateLimiterController {
    @Autowired
    private OrderSrvice orderService;

    //创建令牌桶实例
    private RateLimiter rateLimiter = RateLimiter.create(40);

    @GetMapping("sale")
    public String sale(Integer id) {
        //1.没有获取到token请求一直知道获取到token 令牌
        //log.info("等待的时间: "+  rateLimiter.acquire());
        //2.设置一个等待时间,如果在等待的时间内获取到了token 令牌,则处理业务,如果在等待时间内没有获取到响应token则抛弃
        if (!rateLimiter.tryAcquire(2, TimeUnit.SECONDS)) {
            System.out.println("当前请求被限流,直接抛弃,无法调用后续秒杀逻辑....");
            return "抢购失败!";
        }
        System.out.println("处理业务.....................");
        return "抢购成功";
    }
}
