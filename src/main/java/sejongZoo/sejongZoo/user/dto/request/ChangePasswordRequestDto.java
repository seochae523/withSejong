package sejongZoo.sejongZoo.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ChangePasswordRequestDto {
    @NotBlank(message = "Student Id Not Found.")
    @Schema(description = "학번", example = "19011584")
    private String studentId;

    @NotBlank(message = "Password Not Found.")
    @Schema(description = "비번", example = "123132321312")
    private String password;
}
