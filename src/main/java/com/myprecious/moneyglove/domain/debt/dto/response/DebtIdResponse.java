package com.myprecious.moneyglove.domain.debt.dto.response;

import com.myprecious.moneyglove.domain.debt.DebtEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DebtIdResponse {
    private Long id;

    public DebtIdResponse(DebtEntity debtEntity){
        this.id = debtEntity.getId();
    }
}
