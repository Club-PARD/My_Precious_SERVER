package com.myprecious.moneyglove.domain.mail;

import com.myprecious.moneyglove.domain.board.entity.BoardEntity;
import com.myprecious.moneyglove.domain.board.repository.BoardRepository;
import com.myprecious.moneyglove.domain.mail.dto.request.MailDto;
import com.myprecious.moneyglove.domain.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
public class MailComponent {
    private final MailService mailService;
    private final BoardRepository boardRepository;

    public MailComponent(MailService mailService, BoardRepository boardRepository) {
        this.mailService = mailService;
        this.boardRepository = boardRepository;
    }

//    @Scheduled(cron = "0 0 0 * * *") 정각
        @Scheduled(fixedRate = 30000)
            public void updateDday() {
        List<BoardEntity> boards = boardRepository.findByBoardStatus(BoardEntity.BoardStatus.NOTYET);

        for (BoardEntity board : boards) {
            board.setDDay(board.getDDay() - 1);
            boardRepository.save(board);
        }
    }
//    @Scheduled(cron = 0 22 * * * *)
    @Scheduled(fixedRate = 30000)
    public void scheduledMailSending() {
        sendMailForDday(3, "[D-3] 돈이 생겼다면 차근차근 갚아가세요.",
                "안녕하세요. 머니글러브입니다.\n" +
                "만약 현재 조금이라도 여유돈이 생겼다면 돈을 빌려준 친구들 한명한명씩 갚아보세요!\n" +
                "나중에 한번에 갚으려면 힘들 수도 있어요.");

        sendMailForDday(0, "[D-day] 오늘은 돈을 모두 갚기로 한 날짜에요. 책임감 있는 모습을 기대할게요.",
                "안녕하세요. 머니글러브입니다.\n" +
                "\n" +
                "오늘은 돈을 갚기로 약속한 날짜입니다!\n" +
                "\n" +
                "머글님을 도와준 친구들은 머글님께서 힘들 때 머글님을 생각해준 소중한 친구입니다.\n" +
                "\n" +
                "이런 소중한 친구들과의 관계를 지키기 위해서는 약속을 지키는 것은 필수겠죠?");

        sendMailForDday(-1, "[D+1] 친구들의 신뢰가 깎이고 있어요. 문제가 생기기 전에 얼른 돈을 갚아주세요.",
                "안녕하세요. 머니글러브입니다.\n" +
                        "\n" +
                        "돈을 갚기로 한 날짜가 지났어요!\n" +
                        "\n" +
                        "머글님을 도와준 친구들은 머글님께서 힘들 때 머글님을 생각해준 소중한 친구입니다.\n" +
                        "\n" +
                        "이런 소중한 친구들과의 관계를 지키기 위해서 지금이라도 사과하고 돈을 갚아주세요.\n" +
                        "\n" +
                        "[주의: 채무불이행은 엄연한 불법입니다. 친구들의 신뢰가 많이 깎이면 법적 문제로 불거질 수 있습니다. " +
                        "머글님이 쓰신 게시물은 삭제 및 수정이 불가능하며 법정에서 불리한 자료로 사용될 수 있습니다. " +
                        "형법 제347조 (사기) 사람을 기망하여 재물의 교부를 받거나 재산상의 이익을 취득한 자는 10년 이하의 징역 또는 2천만원 이하의 벌금에 처한다. " +
                        "민법 제390조 (채무불이행과 손해배상) 채무자가 채무의 내용에 좇은 이행을 하지 아니한 때에는채권자는 손해배상을 청구할 수 있다.]");
    }

    private void sendMailForDday(Integer dDay, String title, String message) {
        List<BoardEntity> boards = boardRepository.findBydDay(dDay);

        for (BoardEntity board : boards) {
            log.info("Gmail ID: {}", board.getUser().getGmailId());

            if (!board.getDebts().isEmpty()) {
                log.info("Board with ID {} has associated debts. Sending mail...", board.getId());
                MailDto mailDto = new MailDto();
                mailDto.setAddress(board.getUser().getGmailId());
                mailDto.setTitle(title);
                mailDto.setMessage(message);
                mailService.mailSend(mailDto);
            } else {
                log.info("Board with ID {} has no associated debts.", board.getId());
            }
        }
    }
}