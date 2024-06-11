package sejongZoo.sejongZoo.chat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
public class ChatRoomSaveRequestDto {
    @NotBlank(message = "Chat Room Publisher Not Found.")
    @Schema(description = "방 생성자", example = "ex")
    private String publisher;

    @NotBlank(message = "Chat Room Subscriber Not Found.")
    @Schema(description = "방 참여자", example = "ex")
    private String subscriber;
    @NotBlank(message = "Board Id Not Found.")
    @Schema(description = "게시판 id", example = "1")
    private Long boardId;
}
