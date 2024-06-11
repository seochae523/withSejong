package sejongZoo.sejongZoo.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.checkerframework.checker.units.qual.A;

@Getter
@NoArgsConstructor
public class UpdateRequestDto {
    @NotBlank(message = "Student Id Not Found.")
    @Schema(description = "학번", example = "1901914912")
    private String studentId;

    @NotBlank(message = "Nickname Not Found.")
    @Schema(description = "별명", example = "정건희 바보")
    private String nickname;

    @NotBlank(message = "Major Not Found.")
    @Schema(description = "전공", example = "컴공")
    private String major;
}
