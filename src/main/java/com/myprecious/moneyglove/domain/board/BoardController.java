package com.myprecious.moneyglove.domain.board;

import com.myprecious.moneyglove.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards") // 공통되는 URL 묶기.
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/{userId}")
    public ResponseDto<BoardResponse> createBoard(@PathVariable final Long userId, @RequestBody final BoardRequest request){
        ResponseDto<BoardResponse> result = boardService.createBoard(userId, request);
        return result;
    }
    @GetMapping("/users/{userId}")
    public ResponseDto<List<BoardResponse>> findAll(@PathVariable Long userId){
        ResponseDto<List<BoardResponse>> result = boardService.findAll(userId);
        return result;
    }
    @GetMapping("/{boardId}")
    public ResponseDto<BoardResponse> findOne(@PathVariable Long boardId ){
        ResponseDto<BoardResponse> result = boardService.findOne(boardId);
        return result;
    }
}
