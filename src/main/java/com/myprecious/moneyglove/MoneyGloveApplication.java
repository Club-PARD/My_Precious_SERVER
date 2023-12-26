package com.myprecious.moneyglove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MoneyGloveApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyGloveApplication.class, args);
    }

}
