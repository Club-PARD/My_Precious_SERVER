package com.myprecious.moneyglove.domain.board;

import lombok.Data;

@Data
public class BoardSimpleResponse {
    private String title;
    private String borrowMoney;
    private String payDate;
    private String bank;
    private String bankAccount;

    public BoardSimpleResponse(BoardEntity board) {
        this.title = board.getTitle();
        this.borrowMoney = board.getBorrowMoney();
        this.payDate = board.getPayDate();
        this.bank = board.getBank();
        this.bankAccount = board.getBankAccount();
    }
}
