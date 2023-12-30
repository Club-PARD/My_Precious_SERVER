package com.myprecious.moneyglove.domain.debt;

import lombok.Data;

import java.util.List;

@Data
public class DebtLendMoneyResponse {
    private String lendMoney;

    public DebtLendMoneyResponse(DebtEntity debt) {
        this.lendMoney = debt.getLendMoney();
    }
}
