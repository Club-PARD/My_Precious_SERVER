package com.myprecious.moneyglove.domain.debt.dto.response;

import com.myprecious.moneyglove.domain.debt.DebtEntity;
import com.myprecious.moneyglove.domain.debt.DebtEntity.DebtStatus;
import com.myprecious.moneyglove.domain.debt.DebtEntity.RepaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DebtStatusResponse {
    private DebtStatus debtStatus;
    private RepaymentStatus repaymentStatus;

    public DebtStatusResponse(DebtEntity debt) {
        this.debtStatus = debt.getDebtStatus();
        this.repaymentStatus = debt.getRepaymentStatus();
    }
}
