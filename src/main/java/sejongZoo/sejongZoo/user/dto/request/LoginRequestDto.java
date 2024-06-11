package sejongZoo.sejongZoo.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "Student Id Not Found.")
    @Schema(description = "학번", example = "19011584")
    private String studentId;

    @NotBlank(message = "Password Not Found.")
    @Schema(description = "비번", example = "123132321312")
    private String password;

}
