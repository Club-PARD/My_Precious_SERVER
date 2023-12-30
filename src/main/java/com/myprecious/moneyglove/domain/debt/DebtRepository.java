package com.myprecious.moneyglove.domain.debt;

import com.myprecious.moneyglove.domain.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebtRepository extends JpaRepository<DebtEntity, Long> {
    List<DebtEntity> findByBoardId(Long boardId);
    List<DebtEntity> findByUser_Uid(String uid);
}
