package com.myprecious.moneyglove.user;

import com.myprecious.moneyglove.user.UserEntity;
import lombok.Builder;
import lombok.Data;


@Data
public class UserSimpleResponse {
    private Long id;
    private String name;
    private String gmailId;
    private String userId;

    @Builder
    public UserSimpleResponse(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.gmailId = user.getGmailId();
        this.userId = user.getUserId();
    }
}