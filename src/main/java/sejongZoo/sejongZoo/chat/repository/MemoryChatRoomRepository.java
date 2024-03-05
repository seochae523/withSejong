package sejongZoo.sejongZoo.chat.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import sejongZoo.sejongZoo.chat.dto.ChatRoomDto;

import java.util.*;

@Repository
public class MemoryChatRoomRepository implements ChatRoomRepository{
    private Map<String, ChatRoomDto> chatRoomDtoMap;

    @Override
    @PostConstruct
    public void init() {
        chatRoomDtoMap = new LinkedHashMap<>();
    }

    @Override
    public List<ChatRoomDto> findAllRooms() {
        List chatRooms = new ArrayList<>(chatRoomDtoMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    @Override
    public ChatRoomDto findRoomById(String roomId) {
        return chatRoomDtoMap.get(roomId);
    }

    @Override
    public ChatRoomDto createRoom(String name) {
        String roomId = UUID.randomUUID().toString();
        ChatRoomDto chatRoom = ChatRoomDto.builder().roomId(roomId).name(name).build();

        chatRoomDtoMap.put(roomId, chatRoom);
        return chatRoom;
    }
}
