package com.myprecious.moneyglove.domain.mail;

import com.myprecious.moneyglove.domain.board.BoardEntity;
import com.myprecious.moneyglove.domain.board.BoardRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.myprecious.moneyglove.domain.board.BoardEntity.BoardStatus.NOTYET;

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
    public void updateDday(){
        BoardEntity board = boardRepository.findByBoardStatus(NOTYET);
        board.setDDay(board.getDDay()-1);
        boardRepository.save(board);
    }

    @Scheduled(fixedRate = 30000)
    public void scheduledMailSending() {
        sendMailForDday(3, "[D-3] 돈 보낼 준비하셈", "3일 남음 ㅅㄱ");
        sendMailForDday(0, "[D-day] 오늘이야", "오늘이라고");
        sendMailForDday(-1, "[D+1] 보내야지???? ", "지났어");
        // 추가적으로 다른 D-day에 대한 메일을 보내는 경우도 여기에 추가할 수 있습니다.
    }

    private void sendMailForDday(int dday, String title, String message) {
        List<BoardEntity> boards = boardRepository.findByDDay(dday);

        for (BoardEntity board : boards) {
            MailDto mailDto = new MailDto();
            mailDto.setAddress(board.getUser().getGmailId());
            mailDto.setTitle(title);
            mailDto.setMessage(message);
            mailService.mailSend(mailDto);
        }
    }

//
//    @Scheduled(cron = "0 0 0 * * ?")
//    public void scheduledMailSending() {
//        // 객체 생성 및 payDate 값 가져오기
//
//        BoardService boardService = null;
////        String boardPayDate = boardService.getBoardPayDate();
////        String address = boardService.getBoardUserGmailId();
//
//        if (PeriodDays(boardPayDate) == 3) {
//            MailDto mailDto = new MailDto();
//            mailDto.setAddress(address);
//            mailDto.setTitle("[D-3] 돈 보낼 준비하셈");
//            mailDto.setMessage("3일 남음 ㅅㄱ");
//            mailService.mailSend(mailDto);
//        }
//        if (PeriodDays(boardPayDate) == 0) {
//            MailDto mailDto = new MailDto();
//            mailDto.setAddress(address);
//            mailDto.setTitle("[D-Day] 돈 보내세요~~");
//            mailDto.setMessage("오늘임!!!");
//            mailService.mailSend(mailDto);
//        }
//        if (PeriodDays(boardPayDate) < 0) {
//            MailDto mailDto = new MailDto();
//            mailDto.setAddress(address);
//            mailDto.setTitle("날짜가 지났습니다!!!!!!!!!!");
//            mailDto.setMessage("빨리!!!!!!!!!!!");
//            mailService.mailSend(mailDto);
//        }
//    }
//
//    private Integer PeriodDays(String boardpayDate) {
//        LocalDate currentDate = LocalDate.now();
//        LocalDate payDate = LocalDate.parse(boardpayDate, formatter);
//        // 날짜 차이 계산
//        Period period = Period.between(currentDate, payDate);
//
//        return period.getDays();
//    }
}
