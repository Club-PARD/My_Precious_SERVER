package com.myprecious.moneyglove.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByUser_Uid(String uid);
    List<BoardEntity> findByBoardStatus(BoardEntity.BoardStatus boardStatus);
    List<BoardEntity> findBydDay(Integer dDay);
}
