package com.myprecious.moneyglove.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private String name;
    private String birth;
    private String phoneNum;
}
