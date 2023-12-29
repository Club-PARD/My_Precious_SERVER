package com.myprecious.moneyglove.domain.board;

import com.myprecious.moneyglove.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
