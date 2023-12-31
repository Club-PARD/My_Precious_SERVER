package com.myprecious.moneyglove.domain.debt.dto.response;

import com.myprecious.moneyglove.domain.debt.DebtEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentStatusResponse {
    private DebtEntity.RepaymentStatus repaymentStatus;

    public RepaymentStatusResponse(DebtEntity debt) {
        this.repaymentStatus = debt.getRepaymentStatus();
    }
}
