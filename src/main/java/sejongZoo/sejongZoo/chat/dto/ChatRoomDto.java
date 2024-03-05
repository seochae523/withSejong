package sejongZoo.sejongZoo.chat.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDto {
    private String roomId;
    private String name;

    @Builder
    public ChatRoomDto(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }
}
