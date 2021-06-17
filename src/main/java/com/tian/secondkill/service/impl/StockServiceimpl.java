package com.tian.secondkill.service.impl;


import com.tian.secondkill.dao.StockDao;
import com.tian.secondkill.pojo.Stock;
import com.tian.secondkill.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceimpl implements StockService {
    @Autowired
    StockDao stockDao;

    @Override
    public Stock checkStock(int id) {
        return stockDao.checkStock(id);
    }
}
