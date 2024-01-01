package com.myprecious.moneyglove.domain.board.dto.response;

import com.myprecious.moneyglove.domain.board.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDDayResponse {
    private Integer DDay;

    public BoardDDayResponse(BoardEntity boardEntity){
        this.DDay = boardEntity.getDDay();
    }
}
