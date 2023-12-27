package com.myprecious.moneyglove.dto.Board;

import com.myprecious.moneyglove.entity.BoardEntity;
import lombok.Data;

@Data
public class BoardResponse {
    private String title;
    private Float borrowMoney;
    private Integer payYear;
    private Integer payMonth;
    private Integer payDay;
    private String situation;
    private String payWay;
    private String bank;
    private String bankAccount;

    public BoardResponse(BoardEntity board){
        this.title = board.getTitle();
        this.borrowMoney = board.getBorrowMoney();
        this.payYear = board.getPayYear();
        this.payMonth = board.getPayMonth();
        this.payDay = board.getPayDay();
        this.payWay = board.getPayWay();
        this.situation = board.getSituation();
        this.bank = board.getBankAccount();
    }
}
