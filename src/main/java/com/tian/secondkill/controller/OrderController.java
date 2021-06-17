package com.tian.secondkill.controller;


import com.tian.secondkill.service.OrderSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderSrvice orderSrvice;

    /*@RequestMapping("/shop/{id}")
    public String kill(@PathVariable("id") int id){
        System.out.println("秒杀的商品id为"+id);
        try {
            int killId = orderSrvice.kill(id);
            return "秒杀成功，订单id为"+String.valueOf(killId);
        }catch (Exception e){
            e.getMessage();
            return e.getMessage();
        }
    }*/  //单线程情况下比较安全，但是多线程高并发情况下会发生超卖情况！考虑可以加synchronized

    /**
     *加 synchronized 解决超卖现象，但是效率不高，同一时间只允许一个线程访问！
     * @param id
     * @return
     */
    @RequestMapping("/shop/{id}")
    public  String kill(@PathVariable("id") int id){
        System.out.println("秒杀的商品id为"+id);
        try {
            //加 synchronized同步代码块 来解决超卖现象！
            synchronized (this){
                int killId = orderSrvice.kill(id);
                return "秒杀成功，订单id为"+String.valueOf(killId);
            }
        }catch (Exception e){
            e.getMessage();
            return e.getMessage();
        }
    }
}
