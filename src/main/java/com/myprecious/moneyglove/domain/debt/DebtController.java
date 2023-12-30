package com.myprecious.moneyglove.domain.debt;

import com.myprecious.moneyglove.common.ResponseDto;
import com.myprecious.moneyglove.domain.board.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/debts") // 공통되는 URL 묶기.
public class DebtController {

    private final DebtService debtService;
    @PostMapping("/boards/{boardId}/users/{uId}")
    public ResponseDto<DebtResponse> createBoard(@PathVariable final String uId,
                                                 @PathVariable final Long boardId,
                                                 @RequestBody final DebtRequest request){
        ResponseDto<DebtResponse> result = debtService.createDebt(uId, boardId, request);
        return result;
    }

    @GetMapping("/boards/{boardId}")
    public ResponseDto<List<DebtResponse>> findAll(@PathVariable Long boardId){
        ResponseDto<List<DebtResponse>> result = debtService.findAll(boardId);
        return result;
    }
}
