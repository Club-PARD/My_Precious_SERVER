package com.myprecious.moneyglove.domain.debt.service;

import com.myprecious.moneyglove.domain.board.entity.BoardEntity;
import com.myprecious.moneyglove.domain.board.repository.BoardRepository;
import com.myprecious.moneyglove.domain.debt.dto.request.DebtRequest;
import com.myprecious.moneyglove.domain.debt.dto.response.*;
import com.myprecious.moneyglove.domain.debt.entity.DebtEntity;
import com.myprecious.moneyglove.domain.debt.repository.DebtRepository;
import com.myprecious.moneyglove.domain.mail.service.MailService;
import com.myprecious.moneyglove.domain.user.entity.UserEntity;
import com.myprecious.moneyglove.domain.user.repository.UserRepository;
import com.myprecious.moneyglove.common.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DebtService {
    private final DebtRepository debtRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final MailService mailService;

    @Autowired
    public DebtService(DebtRepository debtRepository, BoardRepository boardRepository,
                       UserRepository userRepository, MailService mailService) {
        this.debtRepository = debtRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    public ResponseDto<DebtIdResponse> createDebt(String uId, Long boardId, DebtRequest request) {
        UserEntity user = userRepository.findByUid(uId);

        if (user == null) {
            // 유저가 없으면 글 작성을 막음
            return ResponseDto.setFailed("User not found. Cannot create posting.");
        }

        try {
            BoardEntity board = boardRepository.findById(boardId).get();

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
                DebtIdResponse result = new DebtIdResponse(debt);

                mailService.lentMoney(board.getUser().getGmailId());

                return ResponseDto.setSuccess("친구 응원 성공", result);
            } catch (Exception e) {
                log.error("실패!: {}", e);
                return ResponseDto.setFailed("DB Error - 친구 돕기 실패");
            }
        } catch (Exception e) {
            log.error("board 없다!:{}", e);
            return ResponseDto.setFailed("board 없음");
        }
    }

    public ResponseDto<List<DebtResponse>> findAllByBoard(Long boardId) {
        try {
            List<DebtEntity> debts = debtRepository.findByBoardId(boardId);

            List<DebtResponse> debtResponses = debts.stream()
                    .map(DebtResponse::new)
                    .collect(Collectors.toList());

            if (debtResponses.isEmpty()) {
                return ResponseDto.setFailed("작성한 글이 없습니다.");
            }

            int total = debtResponses.size();
            return ResponseDto.setSuccess("작성한 글 목록입니다.", debtResponses, total);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터베이스 오류");
        }
    }

    public ResponseDto<List<DebtResponse>> findAllByUser(String uId) {
        try {
            List<DebtEntity> debts = debtRepository.findByUser_Uid(uId);

            List<DebtResponse> debtResponses = debts.stream()
                    .map(DebtResponse::new)
                    .collect(Collectors.toList());

            if (debtResponses.isEmpty()) {
                return ResponseDto.setFailed("작성한 글이 없습니다.");
            }

            int total = debtResponses.size();
            return ResponseDto.setSuccess("작성한 글 목록입니다.", debtResponses, total);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터베이스 오류");
        }
    }

    private void updateBoardStatus(BoardEntity board) {
        boolean allDebtsPaidAndConfirmed = board.getDebts().stream()
                .allMatch(debt -> debt.getDebtStatus() == DebtEntity.DebtStatus.PAID
                        && debt.getRepaymentStatus() == DebtEntity.RepaymentStatus.CONFIRMED);

        if (allDebtsPaidAndConfirmed) {
            board.setBoardStatus(BoardEntity.BoardStatus.PAIDALL);
            boardRepository.save(board);
            mailService.paidAll(board.getUser().getGmailId());
        }
    }

    public ResponseDto<DebtStatusResponse> markDebtAsPaid(Long debtId) {
        try {
            DebtEntity debt = debtRepository.findById(debtId).get();
            debt.setDebtStatus(DebtEntity.DebtStatus.PAID);
            debtRepository.save(debt);

            mailService.paidOne(debt.getUser().getGmailId());

            DebtStatusResponse debtStatusResponse = new DebtStatusResponse(debt);
            return ResponseDto.setSuccess("성공적으로 업데이트 되었습니다", debtStatusResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

    public ResponseDto<RepaymentStatusResponse> markDebtAsConfirmed(Long debtId) {
        try {
            DebtEntity debt = debtRepository.findById(debtId).get();
            debt.setRepaymentStatus(DebtEntity.RepaymentStatus.CONFIRMED);
            debtRepository.save(debt);

            RepaymentStatusResponse repaymentStatusResponse = new RepaymentStatusResponse(debt);
            updateBoardStatus(debt.getBoard());

            return ResponseDto.setSuccess("성공적으로 업데이트 되었습니다", repaymentStatusResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

    public ResponseDto<DebtResponse> findOne(@PathVariable Long id) {
        try {
            DebtEntity debtEntity = debtRepository.findById(id).get();
            DebtResponse debtResponse = new DebtResponse(debtEntity);

            return ResponseDto.setSuccess("해당 게시물 찾기 성공!", debtResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

    public ResponseDto<List<ConfirmedDebtResponse>> getConfirmedDebt(@PathVariable Long boardId){
        try{
            List<DebtEntity> debts = debtRepository.findByBoardId(boardId);

            List<ConfirmedDebtResponse> confirmedDebtResponses = debts.stream()
                    .filter(debt -> debt.getRepaymentStatus() == DebtEntity.RepaymentStatus.CONFIRMED)
                    .map(ConfirmedDebtResponse::new)
                    .collect(Collectors.toList());

            int total = confirmedDebtResponses.size();

            if (total == 0) {
                return ResponseDto.setFailed("해당하는 confirmedDebtResponse가 없습니다.");
            }

            return ResponseDto.setSuccess("작성한 글 목록입니다.", confirmedDebtResponses, total);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터베이스 오류");
        }
    }
}