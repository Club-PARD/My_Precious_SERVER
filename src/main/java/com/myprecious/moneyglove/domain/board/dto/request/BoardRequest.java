package com.myprecious.moneyglove.domain.board;

import com.myprecious.moneyglove.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {
    private String title;
    private String borrowMoney;
    private String payDate;
    private String situation;
    private String payWay;
    private String bank;
    private String bankAccount;
}
