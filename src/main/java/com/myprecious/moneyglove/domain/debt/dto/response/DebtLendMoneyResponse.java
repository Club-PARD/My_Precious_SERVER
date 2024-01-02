package com.myprecious.moneyglove.domain.debt.dto.response;

import com.myprecious.moneyglove.domain.debt.entity.DebtEntity;
import lombok.Data;

@Data
public class DebtLendMoneyResponse {
    private String lendMoney;

    public DebtLendMoneyResponse(DebtEntity debt) {
        this.lendMoney = debt.getLendMoney();
    }
}
