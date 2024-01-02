package com.myprecious.moneyglove.domain.mail.dto.response;

import com.myprecious.moneyglove.domain.mail.dto.request.UserSendMailDto;
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
