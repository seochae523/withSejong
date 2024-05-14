package sejongZoo.sejongZoo.user.dto.request;

import lombok.Getter;

@Getter
public class EmailSendRequestDto {
    private String title;
    private String content;
    private Long boardId;
}
