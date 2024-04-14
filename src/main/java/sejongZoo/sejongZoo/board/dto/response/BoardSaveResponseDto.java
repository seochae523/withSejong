package sejongZoo.sejongZoo.board.dto.response;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSaveResponseDto {
    private String content;
    private String title;
    private String studentId;
}
