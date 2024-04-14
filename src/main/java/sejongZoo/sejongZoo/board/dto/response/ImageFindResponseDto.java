package sejongZoo.sejongZoo.board.dto.response;


import lombok.*;
import sejongZoo.sejongZoo.board.domain.Image;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageFindResponseDto {
    private Long id;
    private String url;

    public ImageFindResponseDto(Image image){
        this.id = image.getId();
        this.url = image.getUrl();
    }
}
