package com.myprecious.moneyglove.domain.debt.dto.response;

import com.myprecious.moneyglove.domain.debt.DebtEntity;
import lombok.Data;

import java.util.List;

@Data
public class DebtLendMoneyResponse {
    private String lendMoney;

    public DebtLendMoneyResponse(DebtEntity debt) {
        this.lendMoney = debt.getLendMoney();
    }
}
