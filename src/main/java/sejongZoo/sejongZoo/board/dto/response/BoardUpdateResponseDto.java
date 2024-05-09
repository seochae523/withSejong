package sejongZoo.sejongZoo.board.dto.response;

import lombok.*;
import sejongZoo.sejongZoo.board.dto.request.TagUpdateRequestDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardUpdateResponseDto {
    private String content;
    private String title;
    private Integer price;
    private List<ImageUpdateResponseDto> image;
    private List<TagUpdateResponseDto> tag;
}
