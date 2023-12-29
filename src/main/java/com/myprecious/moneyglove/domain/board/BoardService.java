package com.myprecious.moneyglove.service;

import com.myprecious.moneyglove.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

//    public ResponseDto<BoardEntity> createBoard(BoardRequest boardRequest) {
//        String userId = userRequest.getUserId();
//        try {
//            if (userRepository.existsByUserId(userId)) {
//                return ResponseDto.setFailed("같은 아이디의 유저 존재함. 이미 가입된 유저");
//            }
//
//            UserEntity userEntity = userRequest.toEntity();
//            userRepository.save(userEntity);
//            return ResponseDto.setSuccess("유저 등록 완료", userEntity);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed("Database error");
//        }
//    }
}
