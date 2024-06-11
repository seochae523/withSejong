package sejongZoo.sejongZoo.chat.dto.response;


import lombok.*;
import sejongZoo.sejongZoo.chat.domain.ChatRoom;
import sejongZoo.sejongZoo.user.domain.User;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomFindResponseDto {
    private Long roomId;
    private String publisher;
    private String subscriber;
    private Date createdAt;
    private String boardTitle;
}
