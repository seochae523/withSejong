package sejongZoo.sejongZoo.board.dto.response;

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
    private Long id;
    private String title;
    private Integer price;
    private String studentId;
    private Date createdAt;
    private Set<ImageFindResponseDto> image;
    private Set<TagFindResponseDto> tag;
    public BoardFindResponseDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.price = board.getPrice();
        this.studentId = board.getUser().getStudentId();
        this.createdAt = board.getCreatedAt();
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
