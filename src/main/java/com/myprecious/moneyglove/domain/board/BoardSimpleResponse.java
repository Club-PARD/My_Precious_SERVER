package com.myprecious.moneyglove.board;

public class BoardSimpleResponse {
    private String title;
    private Float borrowMoney;
    private String payDate;
    private String bank;
    private String bankAccount;

    public BoardSimpleResponse(BoardEntity board) {
        this.title = board.getTitle();
        this.borrowMoney = board.getBorrowMoney();
        this.payDate = board.getPayDate();
        this.bank = board.getBankAccount();
    }
}
