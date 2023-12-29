package com.myprecious.moneyglove.mail;

import com.myprecious.moneyglove.mail.MailDto;
import com.myprecious.moneyglove.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MailController {
    private final MailService mailService;

    @GetMapping("/mail")
    public String dispMail() {
        return "mail";
    }

    @PostMapping("/mail")
    public void execMail(MailDto mailDto) {
        // 메일 발송
        mailService.mailSend(mailDto);
    }
}
