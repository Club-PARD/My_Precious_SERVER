package com.myprecious.moneyglove.dto.User;

import com.myprecious.moneyglove.entity.UserEntity;
import lombok.Builder;
import lombok.Data;


@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String birth;
    private String phoneNum;
    private String gmailId;
    private String userId;

    @Builder
    public UserResponseDto(UserEntity user){
        this.id = user.getId();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.phoneNum = user.getPhoneNum();
        this.gmailId = user.getGmailId();
        this.userId = user.getUserId();
    }
}