package sejongZoo.sejongZoo.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailSendRequestDto {
    @NotBlank(message = "Title Not Found.")
    @Schema(description = "제목", example = "title")
    private String title;

    @NotBlank(message = "Content Not Found.")
    @Schema(description = "내용", example = "content")
    private String content;

    @NotBlank(message = "Board Id Not Found.")
    @Schema(description = "게시판 아이디", example = "1")
    private Long boardId;
}
