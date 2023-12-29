package com.myprecious.moneyglove.domain.debt;

import com.myprecious.moneyglove.domain.board.BoardEntity;
import com.myprecious.moneyglove.domain.board.BoardRepository;
import com.myprecious.moneyglove.domain.board.BoardResponse;
import com.myprecious.moneyglove.domain.user.UserEntity;
import com.myprecious.moneyglove.domain.user.UserRepository;
import com.myprecious.moneyglove.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DebtSevice {
    private final DebtRepository debtRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public DebtSevice(DebtRepository debtRepository, BoardRepository boardRepository, UserRepository userRepository) {
        this.debtRepository = debtRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public ResponseDto<DebtResponse> createDebt(Long userId, Long boardId, DebtRequest request) {
        UserEntity user = userRepository.findById(userId).get();
        BoardEntity board = boardRepository.findById(boardId).get();
        try {

            DebtEntity debt = DebtEntity.builder()
                    .lendMoney(request.getLendMoney())
                    .message(request.getMessage())
                    .bank(request.getBank())
                    .bankAccount(request.getBankAccount())
                    .user(user)
                    .board(board)
                    .build();
            try {
                debtRepository.save(debt);
                DebtResponse result = new DebtResponse(debt);
                return ResponseDto.setSuccess("친구 응원 성공", result);
            } catch (Exception e) {
                log.error("실패!: {}",e);
                return ResponseDto.setFailed("DB Error - 친구 돕기 실패");
            }
        } catch (Exception e) {
            log.error("board 없다!:{}", e);
            return ResponseDto.setFailed("board 없음");
        }
    }
}
