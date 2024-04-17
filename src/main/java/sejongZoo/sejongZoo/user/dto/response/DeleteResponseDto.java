package sejongZoo.sejongZoo.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class DeleteResponseDto {
    @Schema(description = "학번", example = "123123")
    private String studentId;
    @Schema(description = "이름", example = "name")
    private String name;
}
