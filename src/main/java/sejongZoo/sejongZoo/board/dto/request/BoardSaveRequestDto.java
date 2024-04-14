package sejongZoo.sejongZoo.board.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import sejongZoo.sejongZoo.board.domain.Board;
import sejongZoo.sejongZoo.board.domain.Image;
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
    private String content;
    private String title;
    private String studentId;
    private Integer price;
    private Set<MultipartFile> images;
    public Board toEntity(User user, Set<Image> images){
        return Board.builder()
                .content(content)
                .title(title)
                .price(price)
                .user(user)
                .image(images)
                .build();
    }
    public Board toEntity(User user){
        return Board.builder()
                .content(content)
                .title(title)
                .price(price)
                .user(user)
                .createdAt(new Date())
                .build();
    }
}
