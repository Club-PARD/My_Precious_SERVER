package com.myprecious.moneyglove.dto.Board;

import com.myprecious.moneyglove.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {
    private String title;
    private Float borrowMoney;
    private Integer payYear;
    private Integer payMonth;
    private Integer payDay;
    private String situation;
    private String payWay;
    private String bank;
    private String bankAccount;

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .title(title)
                .borrowMoney(borrowMoney)
                .payYear(payYear)
                .payMonth(payMonth)
                .payDay(payDay)
                .situation(situation)
                .payWay(payWay)
                .bank(bank)
                .bankAccount(bankAccount)
                .build();
    }
}
