package com.myprecious.moneyglove.domain.debt.dto.response;

import com.myprecious.moneyglove.domain.debt.entity.DebtEntity;
import com.myprecious.moneyglove.domain.debt.entity.DebtEntity.DebtStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DebtStatusResponse {
    private DebtStatus debtStatus;

    public DebtStatusResponse(DebtEntity debt) {
        this.debtStatus = debt.getDebtStatus();
    }
}
