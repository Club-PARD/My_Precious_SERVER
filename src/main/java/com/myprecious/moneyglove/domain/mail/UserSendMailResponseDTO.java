package com.myprecious.moneyglove.domain.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSendMailResponseDTO {
    private String address;
    public UserSendMailResponseDTO(UserSendMailDto userSendMailDto) {
        this.address = userSendMailDto.getAddress();
    }
}
