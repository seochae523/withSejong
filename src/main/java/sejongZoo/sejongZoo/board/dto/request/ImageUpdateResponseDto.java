package sejongZoo.sejongZoo.board.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageUpdateResponseDto {
    @Schema(description = "업데이트 된 이미지 id", example = "3")
    private Long id;
    @Schema(description = "업데이트 된 이미지 url", example = "ex.ex.com")
    private String url;
}
