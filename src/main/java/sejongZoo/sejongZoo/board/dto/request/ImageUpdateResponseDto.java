package sejongZoo.sejongZoo.board.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageUpdateResponseDto {
    private Long id;
    private MultipartFile image;
}
