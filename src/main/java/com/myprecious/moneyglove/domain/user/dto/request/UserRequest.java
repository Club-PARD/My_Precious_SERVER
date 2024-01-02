package com.myprecious.moneyglove.domain.user.dto.request;

import com.myprecious.moneyglove.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String birth;
    private String phoneNum;
    private String gmailId;
    private String uid;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .name(name)
                .birth(birth)
                .phoneNum(phoneNum)
                .uid(uid)
                .gmailId(gmailId)
                .build();
    } // builder 패턴으로 선언
}