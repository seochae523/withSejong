package sejongZoo.sejongZoo.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSaveResponseDto {
    @Schema(description = "내용", example = "example")
    private String content;
    @Schema(description = "제목", example = "example")
    private String title;
    @Schema(description = "작성자", example = "example")
    private String studentId;
    private List<String> tags;
}
