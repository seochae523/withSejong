package sejongZoo.sejongZoo.chat.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatSaveRequestDto {
    private Long roomId;
    private String sender;
    private String message;

    @Builder
    public ChatSaveRequestDto(Long roomId, String sender, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }
}
