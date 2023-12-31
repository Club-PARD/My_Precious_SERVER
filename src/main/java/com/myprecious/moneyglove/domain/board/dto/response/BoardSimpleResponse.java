package com.myprecious.moneyglove.domain.board.dto.response;

import com.myprecious.moneyglove.domain.board.entity.BoardEntity;
import com.myprecious.moneyglove.domain.user.dto.response.UserSimpleResponse;
import lombok.Data;

@Data
public class BoardSimpleResponse {
    private String title;
    private String borrowMoney;
    private String payDate;
    private String bank;
    private String bankAccount;
    private UserSimpleResponse user;

    public BoardSimpleResponse(BoardEntity board) {
        this.title = board.getTitle();
        this.borrowMoney = board.getBorrowMoney();
        this.payDate = board.getPayDate();
        this.bank = board.getBank();
        this.bankAccount = board.getBankAccount();
        this.user = new UserSimpleResponse(board.getUser());
    }
}
