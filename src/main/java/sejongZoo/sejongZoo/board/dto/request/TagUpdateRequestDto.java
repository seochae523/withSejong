package sejongZoo.sejongZoo.board.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagUpdateRequestDto {
    @Schema(description = "지울 테그 id", example = "3")
    private Long id;
    @Schema(description = "새로 넣을 테그 명", example = "컴공")
    private String category;
}
