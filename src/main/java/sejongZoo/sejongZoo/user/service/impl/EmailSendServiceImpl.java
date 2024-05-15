package sejongZoo.sejongZoo.user.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import sejongZoo.sejongZoo.common.exception.board.BoardIdNotFound;
import sejongZoo.sejongZoo.common.exception.mail.EmailContentNotFound;
import sejongZoo.sejongZoo.common.exception.mail.EmailTitleNotFound;
import sejongZoo.sejongZoo.user.dto.request.EmailSendRequestDto;
import sejongZoo.sejongZoo.user.dto.response.EmailSendResponseDto;
import sejongZoo.sejongZoo.user.service.EmailSendService;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class EmailSendServiceImpl implements EmailSendService {
    private final MailSender mailSender;
    @Override
    public EmailSendResponseDto sendEmail(EmailSendRequestDto emailSendRequestDto) {
        String title = emailSendRequestDto.getTitle();
        String content = emailSendRequestDto.getContent();
        Long boardId = emailSendRequestDto.getBoardId();

        if(title == null) throw new EmailTitleNotFound();
        if(content == null) throw new EmailContentNotFound();
        if(boardId == null) throw new BoardIdNotFound();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("SejongUsedBookMarket@gmail.com");
        simpleMailMessage.setTo("SejongUsedBookMarket@gmail.com");
        simpleMailMessage.setSubject(emailSendRequestDto.getTitle());
        simpleMailMessage.setText(emailSendRequestDto.getContent() + "\n신고 게시글 Id: " + emailSendRequestDto.getBoardId());
        mailSender.send(simpleMailMessage);

        return EmailSendResponseDto.builder()
                .title(emailSendRequestDto.getTitle())
                .boardId(emailSendRequestDto.getBoardId())
                .content(emailSendRequestDto.getContent())
                .build();

    }
}
