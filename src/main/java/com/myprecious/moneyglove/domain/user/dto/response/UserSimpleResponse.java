package com.myprecious.moneyglove.domain.user.dto.response;

import com.myprecious.moneyglove.domain.user.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
public class UserSimpleResponse {
    private String name;
    private String gmailId;
    private String uid;

    @Builder
    public UserSimpleResponse(UserEntity user) {
        this.name = user.getName();
        this.gmailId = user.getGmailId();
        this.uid = user.getUid();
    }
}