package com.myprecious.moneyglove.domain.board;

import com.myprecious.moneyglove.domain.debt.DebtLendMoneyResponse;
import com.myprecious.moneyglove.domain.user.UserEntity;
import com.myprecious.moneyglove.domain.user.UserRepository;
import com.myprecious.moneyglove.common.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public ResponseDto<BoardResponse> createBoard(String uid, BoardRequest request) {
        try {
            UserEntity user = userRepository.findByUid(uid);

            BoardEntity board = BoardEntity.builder()
                    .title(request.getTitle())
                    .situation(request.getSituation())
                    .payDate(request.getPayDate())
                    .payWay(request.getPayWay())
                    .borrowMoney(request.getBorrowMoney())
                    .bank(request.getBank())
                    .bankAccount(request.getBankAccount())
                    .statuses("NOT_RECEIVED")
                    .user(user)
                    .build();

            try{
                boardRepository.save(board);
                BoardResponse result = new BoardResponse(board);
                return ResponseDto.setSuccess("Successfully create posting",result);
            } catch (Exception e){
                e.printStackTrace();
                return ResponseDto.setFailed("DB Error - Failed to create posting.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("유저 엄슴");
        }
    }

    public ResponseDto<List<BoardResponse>> findAll(String uid) {
        try {
            // userId를 기반으로 사용자가 작성한 모든 글을 찾음
            List<BoardEntity> userBoards = boardRepository.findByUser_Uid(uid);

            // 찾은 글들을 ResponseDto로 변환
            List<BoardResponse> boardResponses = userBoards.stream()
                    .map(BoardResponse::new)
                    .collect(Collectors.toList());

            if (boardResponses.isEmpty()) {
                return ResponseDto.setFailed("작성한 글이 없습니다.");
            }

            int total = boardResponses.size();
            return ResponseDto.setSuccess("작성한 글 목록입니다.", boardResponses, total);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터베이스 오류");
        }
    }


    public ResponseDto<BoardResponse> findOne(Long boardId) {
        try{
            BoardEntity boardEntity = boardRepository.findById(boardId).get();
            BoardResponse boardResponse = new BoardResponse(boardEntity);;
            return ResponseDto.setSuccess("해당 게시물 찾기 성공!", boardResponse);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }


}
