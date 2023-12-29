package com.myprecious.moneyglove.mail;

import com.myprecious.moneyglove.mail.MailDto;
import com.myprecious.moneyglove.service.MailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class MailComponent {
    private final MailService mailService;

    public MailComponent(MailService mailService) {
        this.mailService = mailService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduledMailSending() {

        // 예시: 7일 미만이면 실행
        if (!isDateDifferenceLessThan7Days()) {
            MailDto mailDto = new MailDto();
            mailDto.setAddress("rlacofls294@gmail.com");
            mailDto.setTitle("돈 보내세요");
            mailDto.setMessage("This is a scheduled email for less than 7 days.");
            mailService.mailSend(mailDto);
        }
    }

    private boolean isDateDifferenceLessThan7Days() {
        // 여기에 7일 미만 여부를 확인하는 로직을 작성
        // 예시: 현재 날짜와 7일 이후 날짜를 비교하여 7일 미만이면 true 반환
        LocalDate currentDate = LocalDate.now();
        LocalDate payDate = LocalDate.of(2024,1,10);
        Period period = Period.between(currentDate, payDate);
        return period.getDays() >= 7;
    }
}
