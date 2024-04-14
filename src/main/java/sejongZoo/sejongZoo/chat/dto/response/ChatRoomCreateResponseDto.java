package sejongZoo.sejongZoo.chat.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomCreateResponseDto {
    private String roomId;
    private String roomName;
}
