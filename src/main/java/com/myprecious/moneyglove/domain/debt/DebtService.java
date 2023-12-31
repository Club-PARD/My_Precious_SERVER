package com.myprecious.moneyglove.domain.debt;

import com.myprecious.moneyglove.domain.board.BoardEntity;
import com.myprecious.moneyglove.domain.board.BoardRepository;
import com.myprecious.moneyglove.domain.debt.dto.request.DebtRequest;
import com.myprecious.moneyglove.domain.debt.dto.request.DebtStatusRequest;
import com.myprecious.moneyglove.domain.debt.dto.response.DebtResponse;
import com.myprecious.moneyglove.domain.debt.dto.response.DebtStatusResponse;
import com.myprecious.moneyglove.domain.user.UserEntity;
import com.myprecious.moneyglove.domain.user.UserRepository;
import com.myprecious.moneyglove.common.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
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
            // boardId를 기반으로 해당 보드에 작성된 모든 응원 기록
            List<DebtEntity> debts = debtRepository.findByBoardId(boardId);

            // 찾은 글들을 ResponseDto로 변환
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
            // userId를 기반으로 해당 user가 작성한 모든 응원 기록
            List<DebtEntity> debts = debtRepository.findByUser_Uid(uId);

            // 찾은 글들을 ResponseDto로 변환
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
        // Board의 모든 Debt가 PAID 상태이고, RepaymentStatus가 모두 CONFIRMED 상태인지 확인
        boolean allDebtsPaidAndConfirmed = board.getDebts().stream()
                .allMatch(debt -> debt.getDebtStatus() == DebtEntity.DebtStatus.PAID
                        && debt.getRepaymentStatus() == DebtEntity.RepaymentStatus.CONFIRMED);

        // 만약 모든 Debt가 PAID 상태이고, RepaymentStatus가 모두 CONFIRMED 상태이면 Board의 statuses를 업데이트
        if (allDebtsPaidAndConfirmed) {
            board.setBoardStatus(BoardEntity.BoardStatus.PAIDALL);
            boardRepository.save(board);
        }
    }

    public ResponseDto<DebtStatusResponse> markDebtAsPaid(DebtStatusRequest request) {
        Long debtId = request.getId();
        // 먼저 해당 ID로 빚 엔터티를 찾아옵니다.
        DebtEntity debt = debtRepository.findById(debtId)
                .orElseThrow(() -> new EntityNotFoundException("Debt not found with id: " + debtId));

        // debtStatus를 PAID로 변경합니다.
        debt.setDebtStatus(DebtEntity.DebtStatus.PAID);

        // 변경된 상태를 데이터베이스에 반영합니다.
        debtRepository.save(debt);

        // 변경된 상태에 대한 응답을 생성합니다.
        DebtStatusResponse debtStatusResponse = new DebtStatusResponse(debt);

        // 성공적인 응답을 반환합니다.
        return ResponseDto.setSuccess("성공적으로 업데이트 되었습니다", debtStatusResponse);
    }

    public ResponseDto<DebtStatusResponse> markDebtAsConfirmed(DebtStatusRequest request) {
        Long debtId = request.getId();
        // 먼저 해당 ID로 빚 엔터티를 찾아옵니다.
        DebtEntity debt = debtRepository.findById(debtId)
                .orElseThrow(() -> new EntityNotFoundException("Debt not found with id: " + debtId));

        // debtStatus를 PAID로 변경합니다.
        debt.setRepaymentStatus(DebtEntity.RepaymentStatus.CONFIRMED);

        // 변경된 상태를 데이터베이스에 반영합니다.
        debtRepository.save(debt);

        // 변경된 상태에 대한 응답을 생성합니다.
        DebtStatusResponse debtStatusResponse = new DebtStatusResponse(debt);
        updateBoardStatus(debt.getBoard());

        // 성공적인 응답을 반환합니다.
        return ResponseDto.setSuccess("성공적으로 업데이트 되었습니다", debtStatusResponse);
    }



}