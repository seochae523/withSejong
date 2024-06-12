package sejongZoo.sejongZoo.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@NoArgsConstructor
public class ChangePasswordRequestDto {
    @NotBlank(message = "Student Id Not Found.")
    @Schema(description = "학번", example = "19011584")
    private String studentId;

    @NotBlank(message = "Password Not Found.")
    @Schema(description = "비번", example = "123132321312")
    private String password;
}
