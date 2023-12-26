package com.myprecious.moneyglove.test;

import java.time.LocalDate;
import java.time.Period;

public class LocalDateExample {
    public static void main(String[] args) {

            LocalDate currentDate = LocalDate.now();
            LocalDate payDate = LocalDate.of(2024,1,1);
            Period period = Period.between(currentDate, payDate);

            System.out.println(period.getDays());
    }

}
