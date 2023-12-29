package com.myprecious.moneyglove.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {
    private String title;
    private Float borrowMoney;
    private String payDate;
    private String situation;
    private String payWay;
    private String bank;
    private String bankAccount;

    private String uid;

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .title(title)
                .borrowMoney(borrowMoney)
                .payDate(payDate)
                .situation(situation)
                .payWay(payWay)
                .bank(bank)
                .bankAccount(bankAccount)
                .build();
    }
}
