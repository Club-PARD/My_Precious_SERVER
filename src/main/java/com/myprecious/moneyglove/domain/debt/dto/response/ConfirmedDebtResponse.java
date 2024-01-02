package com.myprecious.moneyglove.domain.debt.dto.response;

import com.myprecious.moneyglove.domain.debt.entity.DebtEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmedDebtResponse {
    private Long id;
    private String lendMoney;
    private DebtEntity.RepaymentStatus repaymentStatus;

    public ConfirmedDebtResponse(DebtEntity debtEntity){
        this.id = debtEntity.getId();
        this.lendMoney = debtEntity.getLendMoney();
        this.repaymentStatus = debtEntity.getRepaymentStatus();
    }
}
