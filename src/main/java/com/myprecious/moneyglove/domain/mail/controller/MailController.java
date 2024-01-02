package com.myprecious.moneyglove.domain.mail;

import com.myprecious.moneyglove.common.ResponseDto;
import com.myprecious.moneyglove.domain.user.UserEntity;
import com.myprecious.moneyglove.domain.user.UserRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
