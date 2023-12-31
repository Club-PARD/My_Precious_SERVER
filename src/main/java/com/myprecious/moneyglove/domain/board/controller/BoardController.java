package com.myprecious.moneyglove.domain.board.controller;

import com.myprecious.moneyglove.common.dto.ResponseDto;
import com.myprecious.moneyglove.domain.board.service.BoardService;
import com.myprecious.moneyglove.domain.board.dto.request.BoardRequest;
import com.myprecious.moneyglove.domain.board.dto.response.BoardDDayResponse;
import com.myprecious.moneyglove.domain.board.dto.response.BoardIdResponse;
import com.myprecious.moneyglove.domain.board.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v23/boards") // 공통되는 URL 묶기.
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/{uId}")
    public ResponseDto<BoardIdResponse> createBoard(@PathVariable final String uId, @RequestBody final BoardRequest request) {
        ResponseDto<BoardIdResponse> result = boardService.createBoard(uId, request);
        return result;
    }

    @GetMapping("/users/{uId}")
    public ResponseDto<List<BoardResponse>> findAll(@PathVariable String uId) {
        ResponseDto<List<BoardResponse>> result = boardService.findAll(uId);
        return result;
    }

    @GetMapping("/{boardId}")
    public ResponseDto<BoardResponse> findOne(@PathVariable Long boardId) {
        ResponseDto<BoardResponse> result = boardService.findOne(boardId);
        return result;
    }

    @GetMapping("/d-day/{boardId}")
    public ResponseDto<BoardDDayResponse> returnDDay(@PathVariable Long boarId){
        ResponseDto<BoardDDayResponse> result = boardService.returnDDay(boarId);
        return result;
    }

    @PatchMapping("/{boardId}")
    public ResponseDto<BoardIdResponse> updateItem(@PathVariable Long boardId, @RequestBody BoardRequest request) {
        ResponseDto<BoardIdResponse> result = boardService.updateBoard(boardId, request);
        return result;
    }

    @DeleteMapping("/{boardId}")
    public ResponseDto<?> deleteBoard(@PathVariable Long boardId) {
        ResponseDto<?> result = boardService.deleteBoard(boardId);
        return result;
    }
}
