package com.tian.secondkill.service.impl;


import com.tian.secondkill.dao.OrderDao;
import com.tian.secondkill.dao.StockDao;
import com.tian.secondkill.pojo.Order;
import com.tian.secondkill.pojo.Stock;
import com.tian.secondkill.service.OrderSrvice;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceimpl implements OrderSrvice {
    @Autowired
    StockDao stockDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public int kill(int id) {
        //判断限时抢购时间是否过期
        if(!stringRedisTemplate.hasKey("kill" + id)){
            throw new RuntimeException("秒杀超时,活动已经结束啦!!!");
        }
        //检验库存
        Stock stock = check(id);
        //更新库存
        updateSale(stock);
        //创建订单信息
        return createOrder(stock);
    }

    //检验库存
    private Stock check(int id){
        Stock stock = stockDao.checkStock(id);
        if(stock.getSale() == (stock.getCount())){
            throw new RuntimeException("库存不足！");
        }
        return stock;
    }

    //扣除库存
    private void updateSale(Stock stock){
        stock.setSale(stock.getSale()+1);
        stockDao.updateSale(stock);
    }

    //创建订单
    private int createOrder(Stock stock){
        Order order = new Order();
        order.setSid(stock.getId());
        order.setName(stock.getName());
        order.setCreate_time(new Date());
        orderDao.createOrder(order);
        return order.getId();
    }
}
