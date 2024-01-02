package com.myprecious.moneyglove.domain.board.repository;

import com.myprecious.moneyglove.domain.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByUser_Uid(String uid);
    List<BoardEntity> findByBoardStatus(BoardEntity.BoardStatus boardStatus);
    List<BoardEntity> findBydDay(Long dDay);
}