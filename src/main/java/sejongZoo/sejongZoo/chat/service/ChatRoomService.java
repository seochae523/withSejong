package sejongZoo.sejongZoo.chat.service;

import sejongZoo.sejongZoo.chat.dto.response.ChatRoomCreateResponseDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomResponseDto;

import java.util.List;

public interface ChatRoomService {
    ChatRoomResponseDto createRoom(String roomName);
    ChatRoomResponseDto removeRoom(String roomId);
    ChatRoomResponseDto findByRoomId(String roomId);
    List<ChatRoomResponseDto> findAll();
}
