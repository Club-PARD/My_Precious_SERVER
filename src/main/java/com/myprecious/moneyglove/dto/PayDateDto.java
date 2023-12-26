package com.myprecious.moneyglove.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PayDateDto {
    private int year;
    private int month;
    private int day;
}
