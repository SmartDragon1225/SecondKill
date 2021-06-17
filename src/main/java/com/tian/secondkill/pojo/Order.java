package com.tian.secondkill.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * 订单表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private int sid;
    private String name;
    private Date create_time;
}
