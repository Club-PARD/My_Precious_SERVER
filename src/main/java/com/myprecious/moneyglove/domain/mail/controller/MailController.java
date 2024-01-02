package com.myprecious.moneyglove.domain.mail.controller;

import com.myprecious.moneyglove.common.dto.ResponseDto;
import com.myprecious.moneyglove.domain.mail.dto.request.MailDto;
import com.myprecious.moneyglove.domain.mail.dto.response.MailResponseDto;
import com.myprecious.moneyglove.domain.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v9")
public class MailController {
    private final MailService mailService;

    @PostMapping("/mails")
    public ResponseDto<MailResponseDto> mailSend(@RequestBody MailDto mailDto) {
        ResponseDto<MailResponseDto> result = mailService.mailSend(mailDto);
        return result;
    }

}
