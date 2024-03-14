package sejongZoo.sejongZoo.chat.repository;

import sejongZoo.sejongZoo.chat.dto.response.ChatRoomResponseDto;

import java.util.List;

public interface ChatRoomRepository {
    void init();
    List<ChatRoomResponseDto> findAllRooms();
    ChatRoomResponseDto findRoomById(String roomId);
    ChatRoomResponseDto createRoom(String name);
}
