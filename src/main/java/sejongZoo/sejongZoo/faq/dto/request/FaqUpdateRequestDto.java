package sejongZoo.sejongZoo.faq.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import sejongZoo.sejongZoo.faq.domain.Faq;

import java.util.Date;

@Getter
@NoArgsConstructor
public class FaqUpdateRequestDto {
    @NotNull(message = "Faq Id Not Found.")
    @Schema(description = "자주 묻는 질문 id", example = "1")
    private String id;

    @NotBlank(message = "Faq Title Not Found.")
    @Schema(description = "자주 묻는 질문 제목", example = "example")
    private String title;

    @NotBlank(message = "Faq context Not Found.")
    @Schema(description = "자주 묻는 질문 내용", example = "example")
    private String context;

    @NotNull(message = "Faq Created At Not Found.")
    @Schema(description = "자주 묻는 질문 생성일")
    private Date createdAt;
}
