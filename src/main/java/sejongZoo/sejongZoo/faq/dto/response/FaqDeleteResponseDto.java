package sejongZoo.sejongZoo.faq.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class FaqDeleteResponseDto {
    private String title;
    private String context;
}
