package com.myprecious.moneyglove.domain.debt;

import com.myprecious.moneyglove.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DebtRequest {

    private String lendMoney;
    private String message;
    private String bank;
    private String bankAccount;

//    public DebtEntity toEntity(){
//        return DebtEntity.builder()
//                .lendMoney(lendMoney)
//                .message(message)
//                .bank(bank)
//                .bankAccount(bankAccount)
//                .build();
//    } // builder 패턴으로 선언
}
