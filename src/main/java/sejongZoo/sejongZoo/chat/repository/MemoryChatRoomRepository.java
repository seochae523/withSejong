package sejongZoo.sejongZoo.chat.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomResponseDto;

import java.util.*;

@Repository
public class MemoryChatRoomRepository implements ChatRoomRepository{
    private Map<String, ChatRoomResponseDto> chatRoomDtoMap;

    @Override
    @PostConstruct
    public void init() {
        chatRoomDtoMap = new LinkedHashMap<>();
    }

    @Override
    public List<ChatRoomResponseDto> findAll() {
        List chatRooms = new ArrayList<>(chatRoomDtoMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    @Override
    public ChatRoomResponseDto findRoomById(String roomId) {
        return chatRoomDtoMap.get(roomId);
    }

    @Override
    public ChatRoomResponseDto createRoom(String name) {
        String roomId = UUID.randomUUID().toString();
        ChatRoomResponseDto chatRoom = ChatRoomResponseDto.builder().roomId(roomId).name(name).build();

        chatRoomDtoMap.put(roomId, chatRoom);
        return chatRoom;
    }
}
