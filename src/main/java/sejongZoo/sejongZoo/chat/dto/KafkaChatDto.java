package sejongZoo.sejongZoo.chat.dto;

import lombok.*;
import sejongZoo.sejongZoo.chat.dto.request.ChatSaveRequestDto;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaChatDto {
    private Long roomId;
    private String message;
    private String sender;
    private Date createdAt;

    public KafkaChatDto(ChatSaveRequestDto chatSaveRequestDto, Date createdAt){
        this.roomId = chatSaveRequestDto.getRoomId();
        this.message = chatSaveRequestDto.getMessage();
        this.sender = chatSaveRequestDto.getSender();
        this.createdAt = createdAt;
    }
}
