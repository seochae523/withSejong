package sejongZoo.sejongZoo.user.dto.response;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailSendResponseDto {
    private String title;
    private String content;
    private Long boardId;
}
