package com.myprecious.moneyglove.domain.board;

import com.myprecious.moneyglove.domain.user.UserSimpleResponse;
import lombok.Data;

@Data
public class BoardSimpleResponse {
    private String title;
    private String borrowMoney;
    private String payDate;
    private String bank;
    private String bankAccount;
    private UserSimpleResponse userSimpleResponse;

    public BoardSimpleResponse(BoardEntity board) {
        this.title = board.getTitle();
        this.borrowMoney = board.getBorrowMoney();
        this.payDate = board.getPayDate();
        this.bank = board.getBank();
        this.bankAccount = board.getBankAccount();
        this.userSimpleResponse = new UserSimpleResponse(board.getUser());
    }
}
