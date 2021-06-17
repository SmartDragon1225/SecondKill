package com.tian.secondkill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//利用 synchronized 解决超卖现象！
public class SecondKillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondKillApplication.class, args);
    }

}
