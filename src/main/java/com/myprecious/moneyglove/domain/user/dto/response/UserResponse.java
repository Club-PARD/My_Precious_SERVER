package com.myprecious.moneyglove.domain.user;

import com.myprecious.moneyglove.domain.board.dto.response.BoardSimpleResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponse {
    private String name;
    private String birth;
    private String phoneNum;
    private String gmailId;
    private String uid;
    private List<BoardSimpleResponse> boards;

    @Builder
    public UserResponse(UserEntity user) {
        this.uid = user.getUid();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.phoneNum = user.getPhoneNum();
        this.gmailId = user.getGmailId();

        if (user.getBoards() != null) {
            this.boards = user.getBoards().stream()
                    .map(BoardSimpleResponse::new)
                    .collect(Collectors.toList());
        }
    }
}