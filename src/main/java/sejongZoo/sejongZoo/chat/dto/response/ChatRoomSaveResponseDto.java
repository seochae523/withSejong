package sejongZoo.sejongZoo.chat.dto.response;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomSaveResponseDto {
    private String publisher;
    private String subscriber;
    private Date createdAt;
}