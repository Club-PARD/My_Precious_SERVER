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

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DebtService {
    private final DebtRepository debtRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public DebtService(DebtRepository debtRepository, BoardRepository boardRepository, UserRepository userRepository) {
        this.debtRepository = debtRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public ResponseDto<DebtResponse> createDebt(String uId, Long boardId, DebtRequest request) {
        UserEntity user = userRepository.findByUid(uId);
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

//    public ResponseDto<List<DebtResponse>> findAll(Long boardId) {
//        try {
//            // userId를 기반으로 사용자가 작성한 모든 글을 찾음
//            List<BoardEntity> userBoards = boardRepository.findByUserId(id);
//
//            // 찾은 글들을 ResponseDto로 변환
//            List<BoardResponse> boardResponses = userBoards.stream()
//                    .map(BoardResponse::new)
//                    .collect(Collectors.toList());
//
//            if (boardResponses.isEmpty()) {
//                return ResponseDto.setFailed("작성한 글이 없습니다.");
//            }
//
//            int total = boardResponses.size();
//            return ResponseDto.setSuccess("작성한 글 목록입니다.", boardResponses, total);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed("데이터베이스 오류");
//        }
//    }
    //findAll-board의 전체
    //findAll-user의 전체
    //findOne-해당 debtㄴㅐ
}
