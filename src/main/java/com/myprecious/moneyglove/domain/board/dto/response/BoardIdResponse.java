package com.myprecious.moneyglove.domain.board.dto.response;

import com.myprecious.moneyglove.domain.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardIdResponse {
    private Long id;
    public BoardIdResponse(BoardEntity boardEntity){
        this.id = boardEntity.getId();
    }
}
