package sejongZoo.sejongZoo.chat.service;

import sejongZoo.sejongZoo.chat.dto.KafkaChatDto;
import sejongZoo.sejongZoo.chat.dto.request.ChatSaveRequestDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatFindResponseDto;

public interface KafkaChatService {
    void send(String topic, KafkaChatDto kafkaChatDto);
    void consume(KafkaChatDto kafkaChatDto);
}
