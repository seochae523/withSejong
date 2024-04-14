package sejongZoo.sejongZoo.board.dto.response;

import lombok.*;
import sejongZoo.sejongZoo.board.domain.Tag;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagFindResponseDto {
    private Long id;
    private String category;

    public TagFindResponseDto(Tag tag){
        this.id = tag.getId();
        this.category = tag.getCategory();
    }
}
