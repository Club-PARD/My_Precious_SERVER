package com.myprecious.moneyglove.domain.debt;

import com.myprecious.moneyglove.common.ResponseDto;
import com.myprecious.moneyglove.domain.debt.dto.request.DebtRequest;
import com.myprecious.moneyglove.domain.debt.dto.request.DebtStatusRequest;
import com.myprecious.moneyglove.domain.debt.dto.response.DebtResponse;
import com.myprecious.moneyglove.domain.debt.dto.response.DebtStatusResponse;
import com.myprecious.moneyglove.domain.debt.dto.response.RepaymentStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v9/debts") // 공통되는 URL 묶기.
public class DebtController {

    private final DebtService debtService;

    @PostMapping("/boards/{boardId}/users/{uId}")
    public ResponseDto<DebtResponse> createBoard(@PathVariable final String uId,
                                                 @PathVariable final Long boardId,
                                                 @RequestBody final DebtRequest request) {
        ResponseDto<DebtResponse> result = debtService.createDebt(uId, boardId, request);
        return result;
    }

    @GetMapping("/boards/{boardId}")
    public ResponseDto<List<DebtResponse>> findAllByBoard(@PathVariable Long boardId) {
        ResponseDto<List<DebtResponse>> result = debtService.findAllByBoard(boardId);
        return result;
    }

    @GetMapping("/users/{uId}")
    public ResponseDto<List<DebtResponse>> findAllByUser(@PathVariable String uId) {
        ResponseDto<List<DebtResponse>> result = debtService.findAllByUser(uId);
        return result;
    }

    @PatchMapping("/check-paid-boxes/{debtId}")
    public ResponseDto<DebtStatusResponse> checkpaidbox(@PathVariable Long debtId){
        ResponseDto<DebtStatusResponse> result = debtService.markDebtAsPaid(debtId);
        return result;
    }

    @PatchMapping("/check-confirmed-boxes/{debtId}")
    public ResponseDto<RepaymentStatusResponse> checkconfirmedbox(@PathVariable Long debtId){
        ResponseDto<RepaymentStatusResponse> result = debtService.markDebtAsConfirmed(debtId);
        return result;
    }

    @GetMapping("/{debtId}")
    public ResponseDto<DebtResponse> findone(@PathVariable Long debtId){
        ResponseDto<DebtResponse> result = debtService.findOne(debtId);
        return result;
    }
}
