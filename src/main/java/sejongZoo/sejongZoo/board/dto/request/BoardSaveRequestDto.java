package sejongZoo.sejongZoo.board.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import sejongZoo.sejongZoo.board.domain.Board;
import sejongZoo.sejongZoo.board.domain.Image;
import sejongZoo.sejongZoo.board.domain.Tag;
import sejongZoo.sejongZoo.user.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSaveRequestDto {

    @Schema(description = "내용", example = "이 편지는 1970년 시작되어...")
    private String content;
    @Schema(description = "제목", example = "행운의 편지")
    private String title;
    @Schema(description = "학번", example = "19011584")
    private String studentId;
    @Schema(description = "가격", example = "2999999")
    private Integer price;
    @Schema(description = "새로 넣을 테그 명 : 리스트입니당", example = "[\"컴공\", \"뭐시기\", \"뭐시기\"]" )
    private List<String> tags;
    public Board toEntity(User user, Set<Image> images){
        return Board.builder()
                .content(content)
                .title(title)
                .price(price)
                .user(user)
                .image(images)
                .deleted(false)
                .status(0)
                .build();
    }
    public Board toEntity(User user, Set<Image> images, Set<Tag> tags){
        return Board.builder()
                .content(content)
                .title(title)
                .price(price)
                .user(user)
                .image(images)
                .deleted(false)
                .status(0)
                .tag(tags)
                .build();
    }
    public Board toEntity(User user){
        return Board.builder()
                .content(content)
                .title(title)
                .price(price)
                .user(user)
                .createdAt(new Date())
                .deleted(false)
                .build();
    }
}
