package sejongZoo.sejongZoo.chat.service;

import sejongZoo.sejongZoo.chat.dto.request.ChatRoomSaveRequestDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomFindResponseDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomSaveResponseDto;

import java.util.List;

public interface ChatRoomService {
    ChatRoomSaveResponseDto save(ChatRoomSaveRequestDto chatRoomSaveRequestDto);
    void delete(Long roomId);
    List<ChatRoomFindResponseDto> findByStudentId(String studentId);
}
