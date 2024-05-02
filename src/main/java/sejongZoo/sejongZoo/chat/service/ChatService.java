package sejongZoo.sejongZoo.chat.service;

import sejongZoo.sejongZoo.chat.dto.request.ChatSaveRequestDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatFindResponseDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatSaveResponseDto;

import java.util.List;

public interface ChatService {
    ChatSaveResponseDto chat(ChatSaveRequestDto chatSaveRequestDto);
    List<ChatFindResponseDto> findAll(Long roomId);
}
