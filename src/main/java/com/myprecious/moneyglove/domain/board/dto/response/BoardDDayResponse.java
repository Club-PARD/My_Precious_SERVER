package com.myprecious.moneyglove.domain.board.dto.response;

import com.myprecious.moneyglove.domain.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDDayResponse {
    private Long DDay;

    public BoardDDayResponse(BoardEntity boardEntity){
        this.DDay = boardEntity.getDDay();
    }
}
