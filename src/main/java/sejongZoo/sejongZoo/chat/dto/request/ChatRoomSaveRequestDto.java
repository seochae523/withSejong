package sejongZoo.sejongZoo.chat.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ChatRoomSaveRequestDto {
    private String publisher;
    private String subscriber;
}
