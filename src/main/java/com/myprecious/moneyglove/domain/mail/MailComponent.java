package com.myprecious.moneyglove.domain.mail;

import com.myprecious.moneyglove.domain.board.BoardEntity;
import com.myprecious.moneyglove.domain.board.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.myprecious.moneyglove.domain.board.BoardEntity.BoardStatus.NOTYET;

@Slf4j
@Component
public class MailComponent {
    private final MailService mailService;
    private final BoardRepository boardRepository;

    // 날짜 포맷 정의
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public MailComponent(MailService mailService, BoardRepository boardRepository) {
        this.mailService = mailService;
        this.boardRepository = boardRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void updateDday() {
        List<BoardEntity> boards = boardRepository.findByBoardStatus(BoardEntity.BoardStatus.NOTYET);

        for (BoardEntity board : boards) {
            board.setDDay(board.getDDay() - 1);
            boardRepository.save(board);
        }
    }

    @Scheduled(fixedRate = 30000)
    public void scheduledMailSending() {
        sendMailForDday(3, "[D-3] 돈 보낼 준비하셈", "3일 남음 ㅅㄱ");
        sendMailForDday(0, "[D-day] 오늘이야", "오늘이라고");
        sendMailForDday(-1, "[D+1] 보내야지???? ", "지났어");
        // 추가적으로 다른 D-day에 대한 메일을 보내는 경우도 여기에 추가할 수 있습니다.
    }

    private void sendMailForDday(Integer dDay, String title, String message) {
        List<BoardEntity> boards = boardRepository.findBydDay(dDay);

        for (BoardEntity board : boards) {
            log.info("Gmail ID: {}", board.getUser().getGmailId());

            // Check if the board has associated debts
            if (!board.getDebts().isEmpty()) {
                // Handle the case where debts are associated with the board
                log.info("Board with ID {} has associated debts. Sending mail...", board.getId());
                MailDto mailDto = new MailDto();
                mailDto.setAddress(board.getUser().getGmailId());
                mailDto.setTitle(title);
                mailDto.setMessage(message + " (Debts associated with the board: " + board.getDebts().size() + ")");
                mailService.mailSend(mailDto);
            } else {
                // Handle the case where there are no associated debts
                log.info("Board with ID {} has no associated debts.", board.getId());
            }
        }
    }
}
