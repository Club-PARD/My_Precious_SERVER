package com.myprecious.moneyglove.domain.user;

import com.myprecious.moneyglove.domain.board.BoardSimpleResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class UserResponse {
    private Long id;
    private String name;
    private String birth;
    private String phoneNum;
    private String gmailId;
    private String uid;
    private List<BoardSimpleResponse> boards;

    @Builder
    public UserResponse(UserEntity user){
        this.id = user.getId();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.phoneNum = user.getPhoneNum();
        this.gmailId = user.getGmailId();
        this.uid = user.getUid();

        if(user.getBoards() != null){
            this.boards = user.getBoards().stream()
                    .map(BoardSimpleResponse::new)
                    .collect(Collectors.toList());
        }
    }
}