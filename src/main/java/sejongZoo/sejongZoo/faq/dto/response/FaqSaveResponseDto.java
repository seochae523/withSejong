package sejongZoo.sejongZoo.faq.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class FaqSaveResponseDto {
    private String title;
    private String context;
}
