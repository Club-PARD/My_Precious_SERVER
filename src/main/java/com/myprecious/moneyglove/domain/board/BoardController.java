package com.myprecious.moneyglove.domain.board;

import com.myprecious.moneyglove.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards") // 공통되는 URL 묶기.
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/{uId}")
    public ResponseDto<BoardResponse> createBoard(@PathVariable final String uId, @RequestBody final BoardRequest request) {
        ResponseDto<BoardResponse> result = boardService.createBoard(uId, request);
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
}
