package com.myprecious.moneyglove.domain.board.service;

import com.myprecious.moneyglove.domain.board.entity.BoardEntity;
import com.myprecious.moneyglove.domain.board.repository.BoardRepository;
import com.myprecious.moneyglove.domain.board.dto.request.BoardRequest;
import com.myprecious.moneyglove.domain.board.dto.response.BoardDDayResponse;
import com.myprecious.moneyglove.domain.board.dto.response.BoardIdResponse;
import com.myprecious.moneyglove.domain.board.dto.response.BoardResponse;
import com.myprecious.moneyglove.domain.user.entity.UserEntity;
import com.myprecious.moneyglove.domain.user.repository.UserRepository;
import com.myprecious.moneyglove.common.dto.ResponseDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

    public ResponseDto<BoardIdResponse> createBoard(String uid, BoardRequest request) {
        try {
            UserEntity user = userRepository.findByUid(uid);

            if (user == null) {
                // 유저가 없으면 글 작성을 막음
                return ResponseDto.setFailed("User not found. Cannot create posting.");
            }

            // 입력값이 비어있는지 확인
            if (isRequestInvalid(request)) {
                return ResponseDto.setFailed("Invalid input. Cannot create posting.");
            }

            BoardEntity board = BoardEntity.builder()
                    .title(request.getTitle())
                    .situation(request.getSituation())
                    .payDate(request.getPayDate())
                    .payWay(request.getPayWay())
                    .borrowMoney(request.getBorrowMoney())
                    .bank(request.getBank())
                    .bankAccount(request.getBankAccount())
                    .dDay(PeriodDays(request.getPayDate()))
                    .user(user)
                    .build();

            try {
                boardRepository.save(board);
                BoardIdResponse result = new BoardIdResponse(board);
                return ResponseDto.setSuccess("Successfully create posting", result);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseDto.setFailed("DB Error - Failed to create posting.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("유저 엄슴");
        }
    }

    private boolean isRequestInvalid(BoardRequest request) {
        return !StringUtils.hasText(request.getTitle()) ||
                !StringUtils.hasText(request.getSituation()) ||
                !StringUtils.hasText(request.getPayDate()) ||
                !StringUtils.hasText(request.getBorrowMoney())||
                !StringUtils.hasText(request.getBank()) ||
                !StringUtils.hasText(request.getBankAccount());
    }
    //!StringUtils.hasText(request.getPayWay()) ||

    //d-day 계산
    private Integer PeriodDays(String boardpayDate) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        LocalDate currentDate = LocalDate.now();

        LocalDate payDate = LocalDate.parse(boardpayDate, formatter);

        return Math.toIntExact(ChronoUnit.DAYS.between(currentDate, payDate));
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
        try {
            BoardEntity boardEntity = boardRepository.findById(boardId).get();
            BoardResponse boardResponse = new BoardResponse(boardEntity);
            return ResponseDto.setSuccess("해당 게시물 찾기 성공!", boardResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

    public ResponseDto<BoardDDayResponse> returnDDay(Long boardId){
        try{
            BoardEntity boardEntity = boardRepository.findById(boardId).get();
            BoardDDayResponse boardDDayResponse = new BoardDDayResponse(boardEntity);
            return ResponseDto.setSuccess("해당 게시물의 d-day", boardDDayResponse);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("db 오류");
        }
    }

    public ResponseDto<BoardIdResponse> updateBoard(Long boardId, BoardRequest boardRequest) {
        try {
            BoardEntity boardEntity = boardRepository.findById(boardId).get();

            if (isDebtsEmpty(boardEntity)) {

                if (!isTitleEmpty(boardRequest))
                    boardEntity.setTitle(boardRequest.getTitle());

                if (StringUtils.hasText(boardRequest.getSituation()))
                    boardEntity.setSituation(boardRequest.getSituation());

                if (!isPayWayEmpty(boardRequest))
                    boardEntity.setPayWay(boardRequest.getPayWay());

                if (StringUtils.hasText(boardRequest.getPayDate()))
                    boardEntity.setPayDate(boardRequest.getPayDate());

                if (!isBorrowMoneyEmpty(boardRequest))
                    boardEntity.setBorrowMoney(boardRequest.getBorrowMoney());

                if (!isBankEmpty(boardRequest))
                    boardEntity.setBank(boardRequest.getBank());

                if (StringUtils.hasText(boardRequest.getBankAccount()))
                    boardEntity.setBankAccount(boardRequest.getBankAccount());

                boardRepository.save(boardEntity);

                BoardIdResponse boardIdResponse = new BoardIdResponse(boardEntity);
                return ResponseDto.setSuccess("품목이 업데이트 되었습니다", boardIdResponse);
            }
            log.info("debts : {}", boardEntity.getDebts());
            return ResponseDto.setFailed("board에 작성된 debt가 있습니다");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB 오류");
        }
    }

    private static boolean isDebtsEmpty(BoardEntity boardEntity) {
        return boardEntity.getDebts().isEmpty();
    }

    private static boolean isBankEmpty(BoardRequest boardRequest) {
        return boardRequest.getBank().isEmpty();
    }

    private static boolean isBorrowMoneyEmpty(BoardRequest boardRequest) {
        return boardRequest.getBorrowMoney().isEmpty();
    }

    private static boolean isPayWayEmpty(BoardRequest boardRequest) {
        return boardRequest.getPayWay().isEmpty();
    }

    private static boolean isTitleEmpty(BoardRequest boardRequest) {
        return boardRequest.getTitle().isEmpty();
    }

    public ResponseDto<?> deleteBoard(Long boardId) {
        try {
            BoardEntity boardEntity = boardRepository.findById(boardId).get();

            if (isDebtsEmpty(boardEntity)) {
                if (boardRepository.existsById(boardId)) {
                    boardRepository.deleteById(boardId);
                    return ResponseDto.setSuccess("삭제 되었습니다.", null);
                }
            }
            return ResponseDto.setFailed("debt있음"); // 리스트에 있는 ID인지 확인
        } catch (Exception e) {
            return ResponseDto.setFailed("존재하지 않는 id입니다"); // 리스트에 있는 ID인지 확인
        }
    }
}
