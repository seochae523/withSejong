package sejongZoo.sejongZoo.chat.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatSaveRequestDto {
    @NotNull(message = "Chat Room Id Not Found.")
    @PositiveOrZero(message = "Chat Room Id Must Be Positive Or Zero Value.")
    private Long roomId;

    @NotBlank(message = "Chat Sender Not Found.")
    private String sender;

    @NotBlank(message = "Chat Message Not Found.")
    private String message;

}
