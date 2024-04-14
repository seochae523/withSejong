package sejongZoo.sejongZoo.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpResponseDto {
    @Schema(description = "이름", example = "name")
    private String name;
    @Schema(description = "학번", example = "19012123")
    private String studentId;

    @Builder
    public SignUpResponseDto(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }
}
