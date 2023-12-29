package com.myprecious.moneyglove.domain.board;

import com.myprecious.moneyglove.domain.user.UserSimpleResponse;
import lombok.Data;

@Data
public class BoardResponse {
    private Long id;
    private String title;
    private String borrowMoney;
    private String payDate;
    private String situation;
    private String payWay;
    private String bank;
    private String bankAccount;
    private UserSimpleResponse user;

    public BoardResponse(BoardEntity board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.borrowMoney = board.getBorrowMoney();
        this.payDate = board.getPayDate();
        this.payWay = board.getPayWay();
        this.situation = board.getSituation();
        this.bank = board.getBank();
        this.bankAccount = board.getBankAccount();

        if(board.getUser() != null){
            this.user = new UserSimpleResponse(board.getUser());
        }
    }
}
