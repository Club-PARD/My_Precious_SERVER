package com.myprecious.moneyglove.board;

import lombok.Data;

@Data
public class BoardResponse {
    private String title;
    private Float borrowMoney;
    private String payDate;
    private String situation;
    private String payWay;
    private String bank;
    private String bankAccount;
    private UserSimpleResponse user;

    public BoardResponse(BoardEntity board){
        this.title = board.getTitle();
        this.borrowMoney = board.getBorrowMoney();
        this.payDate = board.getPayDate();
        this.payWay = board.getPayWay();
        this.situation = board.getSituation();
        this.bank = board.getBankAccount();

        if(board.getUser() != null){
            this.user = new UserSimpleResponse(board.getUser());
        }
    }
}
