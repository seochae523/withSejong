package sejongZoo.sejongZoo.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sejongZoo.sejongZoo.board.domain.Board;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private Date date;
    private String content;
    private String title;

    @Builder
    public BoardDto(Date date, String content, String title) {
        this.date = date;
        this.content = content;
        this.title = title;
    }

}
