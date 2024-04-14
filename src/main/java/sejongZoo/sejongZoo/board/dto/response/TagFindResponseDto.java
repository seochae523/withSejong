package sejongZoo.sejongZoo.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import sejongZoo.sejongZoo.board.domain.Tag;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagFindResponseDto {
    @Schema(description = "테그 조회 결과 id - 나중에 update, delete 할때 필요함", example = "3")
    private Long id;
    @Schema(description = "테그 명", example = "컴공")
    private String category;

    public TagFindResponseDto(Tag tag){
        this.id = tag.getId();
        this.category = tag.getCategory();
    }
}
