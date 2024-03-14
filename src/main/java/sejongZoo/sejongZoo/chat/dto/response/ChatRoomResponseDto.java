package sejongZoo.sejongZoo.chat.dto.response;


import lombok.*;
import sejongZoo.sejongZoo.chat.domain.ChatRoom;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomResponseDto {
    private String roomId;
    private String name;

    public ChatRoomResponseDto(ChatRoom chatRoom){
        this.roomId = chatRoom.getRoomId();
        this.name= chatRoom.getRoomName();
    }
}
