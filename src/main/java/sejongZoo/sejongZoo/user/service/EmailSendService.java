package sejongZoo.sejongZoo.user.service;

import sejongZoo.sejongZoo.user.dto.request.EmailSendRequestDto;
import sejongZoo.sejongZoo.user.dto.response.EmailSendResponseDto;

public interface EmailSendService {
    EmailSendResponseDto sendEmail(EmailSendRequestDto emailSendRequestDto);


}
