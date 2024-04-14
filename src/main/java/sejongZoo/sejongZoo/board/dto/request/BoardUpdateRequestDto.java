package sejongZoo.sejongZoo.board.dto.request;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardUpdateRequestDto {
    private Long id;
    private String content;
    private String title;
    private Integer price;
    private String studentId;
    private List<ImageUpdateResponseDto> imageUpdateResponseDto;
}
