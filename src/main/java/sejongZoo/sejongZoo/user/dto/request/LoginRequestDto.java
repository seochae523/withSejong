package sejongZoo.sejongZoo.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {
    @Schema(description = "학번", example = "19011584")
    private String studentId;
    @Schema(description = "비번", example = "123132321312")
    private String password;

    @Builder
    public LoginRequestDto(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }
}
