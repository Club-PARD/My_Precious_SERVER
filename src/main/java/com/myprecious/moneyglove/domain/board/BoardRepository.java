package com.myprecious.moneyglove.domain.board;

import com.myprecious.moneyglove.domain.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByUser_Uid(String uid);
    BoardEntity findByBoardStatus(BoardEntity.BoardStatus boardStatus);

    List<BoardEntity> findByDDay(Integer day);
}
