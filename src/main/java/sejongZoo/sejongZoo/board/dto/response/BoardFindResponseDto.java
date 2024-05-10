package sejongZoo.sejongZoo.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import sejongZoo.sejongZoo.board.domain.Board;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardFindResponseDto {
    @Schema(description = "조회 결과 id 나중에 update, delete 할때 필요함", example = "3")
    private Long id;
    @Schema(description = "제목", example = "example")
    private String title;
    @Schema(description = "가격", example = "12321123")
    private Integer price;
    @Schema(description = "내용", example = "뭘 팔까요")
    private String content;
    @Schema(description = "학번", example = "example")
    private String studentId;
    @Schema(description = "작성일", example = "2024-04-14T17:21:33.975Z")
    private Date createdAt;
    @Schema(description = "이미지들", example = "image :[~~]")
    private Set<ImageFindResponseDto> image;
    @Schema(description = "태그들", example = "tag : [~~]")
    private Set<TagFindResponseDto> tag;
    public BoardFindResponseDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.price = board.getPrice();
        this.studentId = board.getUser().getStudentId();
        this.createdAt = board.getCreatedAt();
        this.content = board.getContent();
        if(board.getImage().isEmpty()){
            this.image = new HashSet<>();
        }
        else {
            this.image = board.getImage().stream()
                    .map(ImageFindResponseDto::new)
                    .collect(Collectors.toSet());
        }
        if(board.getTag().isEmpty()){
            this.tag = new HashSet<>();
        }
        else{
            this.tag = board.getTag().stream()
                    .map(TagFindResponseDto::new)
                    .collect(Collectors.toSet());
        }
    }
}
