package sejongZoo.sejongZoo.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagUpdateResponseDto {
    @Schema(description = "업데이트 된 테그 id", example = "3")
    private Long id;
    @Schema(description = "업데이트 된 테그 명", example = "컴공")
    private String category;
    @Schema(description = "업데이트 된 테그 상태 deleted = 삭제된것, added = 추가된것", example = "deleted")
    private String status;
}
