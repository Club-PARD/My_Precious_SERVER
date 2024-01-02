package com.myprecious.moneyglove.domain.mail.dto.response;

import com.myprecious.moneyglove.domain.mail.dto.request.MailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MailResponseDto {
    private String address;
    private String title;
    private String message;

    public MailResponseDto(MailDto dto) {
        this.address = dto.getAddress();
        this.title = dto.getTitle();
        this.message = dto.getMessage();
    }
}
