package com.myprecious.moneyglove.domain.user;

import com.myprecious.moneyglove.domain.user.UserEntity;
import lombok.Builder;
import lombok.Data;


@Data
public class UserSimpleResponse {
    private String name;
    private String gmailId;
    private String userId;

    @Builder
    public UserSimpleResponse(UserEntity user) {
        this.name = user.getName();
        this.gmailId = user.getGmailId();
        this.userId = user.getUserId();
    }
}