package sejongZoo.sejongZoo.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageDto {
    private String roomId;
    private String sender;
    private String message;

    @Builder
    public MessageDto(String roomId, String sender, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }
}
