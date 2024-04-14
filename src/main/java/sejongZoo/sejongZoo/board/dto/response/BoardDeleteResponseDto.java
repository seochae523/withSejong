package sejongZoo.sejongZoo.board.dto.response;

import sejongZoo.sejongZoo.board.domain.Board;

import java.util.Date;

public class BoardDeleteResponseDto {
    private Date date;
    private String content;
    private String title;
    private Integer price;

    public BoardDeleteResponseDto(Board board){
        this.date = board.getCreatedAt();
        this.content = board.getContent();
        this.title = board.getTitle();
        this.price = board.getPrice();
    }
}
