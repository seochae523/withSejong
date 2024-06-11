package sejongZoo.sejongZoo.user.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejongZoo.sejongZoo.user.dto.request.EmailSendRequestDto;
import sejongZoo.sejongZoo.user.dto.response.EmailSendResponseDto;
import sejongZoo.sejongZoo.user.service.EmailSendService;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailSendServiceImpl implements EmailSendService {
    private final MailSender mailSender;
    @Override
    @Transactional
    public EmailSendResponseDto sendEmail(EmailSendRequestDto emailSendRequestDto) {
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
