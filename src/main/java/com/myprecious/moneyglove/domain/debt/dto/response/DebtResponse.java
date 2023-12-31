package com.myprecious.moneyglove.domain.debt.dto.response;

import com.myprecious.moneyglove.domain.board.BoardSimpleResponse;
import com.myprecious.moneyglove.domain.debt.DebtEntity;
import com.myprecious.moneyglove.domain.user.UserEntity;
import com.myprecious.moneyglove.domain.user.UserSimpleResponse;
import lombok.AllArgsConstructor;
import com.myprecious.moneyglove.domain.debt.DebtEntity.DebtStatus;
import com.myprecious.moneyglove.domain.debt.DebtEntity.RepaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DebtResponse {

    private Long id;
    private String lendMoney;
    private String message;
    private String bank;
    private String bankAccount;
    private DebtStatus debtStatus;
    private RepaymentStatus repaymentStatus;

    private UserSimpleResponse user;
//    private BoardSimpleResponse board;

    public DebtResponse(DebtEntity debt) {
        this.id = debt.getId();
        this.lendMoney = debt.getLendMoney();
        this.message = debt.getMessage();
        this.bank = debt.getBank();
        this.bankAccount = debt.getBankAccount();
        this.debtStatus = debt.getDebtStatus();
        this.repaymentStatus = debt.getRepaymentStatus();

        this.user = new UserSimpleResponse(debt.getUser());
    }

}
