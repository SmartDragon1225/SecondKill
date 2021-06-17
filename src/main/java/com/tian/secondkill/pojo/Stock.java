package com.tian.secondkill.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private int id;
    private String name;
    private int count;
    private int sale;
    private int version;//使用mysql的乐观锁防止超卖
}
