package sejongZoo.sejongZoo.board.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardUpdateRequestDto {
    @Schema(description = "업데이트 할 id 식별용", example = "2")
    private Long id;
    @Schema(description = "업데이트 할 내용", example = "내용내뇽내용")
    private String content;
    @Schema(description = "업데이트 할 제목", example = "제목몾고족")
    private String title;
    @Schema(description = "업데이트 할 가격", example = "12312321213123")
    private Integer price;
    @Schema(description = "학번", example = "19011723")
    private String studentId;
    @Schema(description = "지울 이미지 id", example = "[1]")
    private List<Long> imageId;
    @Schema(description = "지울 테그 id", example = "[1]")
    private List<Long> tagId;
    @Schema(description = "새로 넣을 테그 카테고리", example = "[컴공]")
    private List<String> newTagCategory;
}
