package sejongZoo.sejongZoo.board.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardPullUpResponseDto {
    private Long id;
    private String title;
    private String content;
    private Date createdAt;
    private String studentId;
}
