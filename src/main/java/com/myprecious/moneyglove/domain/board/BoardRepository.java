package com.myprecious.moneyglove.domain.board;

import com.myprecious.moneyglove.domain.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
