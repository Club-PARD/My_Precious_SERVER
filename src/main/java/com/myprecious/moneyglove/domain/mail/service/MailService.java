package com.myprecious.moneyglove.domain.mail.service;

import com.myprecious.moneyglove.common.dto.ResponseDto;
import com.myprecious.moneyglove.domain.mail.dto.request.MailDto;
import com.myprecious.moneyglove.domain.mail.dto.response.MailResponseDto;
import com.myprecious.moneyglove.domain.mail.dto.response.UserSendMailResponseDTO;
import com.myprecious.moneyglove.common.util.MailHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@Service
public class MailService {
    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "YOUR_EMAIL_ADDRESS";

    // 생성자에서 JavaMailSender 주입
    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public ResponseDto<MailResponseDto> mailSend(MailDto mailDto) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);

            mailHandler.setTo(mailDto.getAddress());
            mailHandler.setFrom(MailService.FROM_ADDRESS);

            mailHandler.setSubject(mailDto.getTitle());
            String htmlContent = "<p>" + mailDto.getMessage() +
                    "<p><a href=\"https://precious-relationship.web.app\">Money Glove</a></p>";
            mailHandler.setText(htmlContent, true);


            mailHandler.send();

            MailResponseDto mailResponseDto = new MailResponseDto(mailDto);
            return ResponseDto.setSuccess("메일 전송 성공",mailResponseDto);
        }
        catch(Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("메일 전송 중 오류 발생: " + sw.toString() + e.getMessage());
            return ResponseDto.setFailed(e.getMessage());
        }
    }

    public ResponseDto<UserSendMailResponseDTO> lentMoney(String userAddress){
        try {
            MailHandler mailHandler = new MailHandler(mailSender);

            mailHandler.setTo(userAddress);
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            mailHandler.setSubject("[MoneyGlove] 친구가 돈을 빌려줬어요! 힘들 때 도와준 친구에게 편지로 고마움을 표현해주세요.");

            String htmlContent = "<html><body>" +
                    "<p>안녕하세요. 머니글러브입니다.</p>" +
                    "<p>친구가 돈을 빌려줬어요!</p>" +
                    "<p>지금 게시물 관리 페이지로 이동하여 확인하고 감사편지를 보내보세요.</p>" +
                    "<p>감사편지 한장이 머글님의 친구들에게 큰 기쁨이 될거예요.</p>" +
                    "<p>[만약 빌려준 돈과 실제로 계좌에 들어온 금액이 다르다면 moneyglove2024@gmail.com로 문의주세요.]</p>" +
                    "<p><a href=\"https://precious-relationship.web.app\">Money Glove</a></p>" +
                    "</body></html>";

            mailHandler.setText(htmlContent, true);


            mailHandler.send();
            UserSendMailResponseDTO userSendMailResponseDTO = new UserSendMailResponseDTO(userAddress);
            return ResponseDto.setSuccess("메일 전송 성공", userSendMailResponseDTO);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("전송 실패!");
        }
    }

    public ResponseDto<UserSendMailResponseDTO> paidAll(String userAddress){
        try {
            MailHandler mailHandler = new MailHandler(mailSender);

            mailHandler.setTo(userAddress);
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            mailHandler.setSubject("[MoneyGlove] 모든 돈을 갚으셨군요! 축하합니다.");

            String htmlContent = "<html><body>" +
                    "<p>안녕하세요. 머니글러브입니다.</p>" +
                    "<p>빌린 모든 금액을 갚으셨습니다.</p>" +
                    "<p>머글님의 친구관계를 지키기 위한 책임감 있는 모습에 박수를 보내드립니다!!!</p>" +
                    "<p>한때 힘들었던 시간 함께 도와줬던 친구들에게 감사하다고 한번 더 말해보는 것은 어떨까요?</p>" +
                    "<p>지금까지 “돈도 지키고 우정도 지키자!” MoneyGlove를 이용해주셔서 감사합니다.</p>" +
                    "<p><a href=\"https://precious-relationship.web.app\">Money Glove</a></p>" +
                    "</body></html>";

            mailHandler.setText(htmlContent, true);


            mailHandler.send();
            UserSendMailResponseDTO userSendMailResponseDTO = new UserSendMailResponseDTO(userAddress);
            return ResponseDto.setSuccess("메일 전송 성공", userSendMailResponseDTO);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("전송 실패!");
        }
    }

    public ResponseDto<UserSendMailResponseDTO> paidOne(String userAddress){
        try {
            MailHandler mailHandler = new MailHandler(mailSender);

            mailHandler.setTo(userAddress);
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            mailHandler.setSubject("[MoneyGlove] 친구가 돈을 갚았다고 합니다. 확인해주세요");

            String htmlContent = "<html><body>" +
                    "<p>안녕하세요. 머니글러브입니다.</p>" +
                    "<p>친구가 돈을 갚았다고 합니다.</p>" +
                    "<p>지금 바로 확인 후에 확인 버튼을 눌러주세요!!</p>" +
                    "<p>MoneyGlove를 통해 친구와 꾸준히 소통해보아요 희힣ㅅ.</p>" +
                    "<p><a href=\"https://precious-relationship.web.app\">Money Glove</a></p>" +
                    "</body></html>";

            mailHandler.setText(htmlContent, true);

            mailHandler.send();
            UserSendMailResponseDTO userSendMailResponseDTO = new UserSendMailResponseDTO(userAddress);
            return ResponseDto.setSuccess("메일 전송 성공", userSendMailResponseDTO);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("전송 실패!");
        }
    }
}
