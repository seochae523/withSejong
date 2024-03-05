package sejongZoo.sejongZoo.chat.repository;

import sejongZoo.sejongZoo.chat.dto.ChatRoomDto;

import java.util.List;

public interface ChatRoomRepository {
    void init();
    List<ChatRoomDto> findAllRooms();
    ChatRoomDto findRoomById(String roomId);
    ChatRoomDto createRoom(String name);
}
