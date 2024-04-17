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
public class UpdateResponseDto {
    @Schema(description = "학번", example = "123123")
    private String studentId;
    @Schema(description = "닉네임", example = "nickname")
    private String nickname;
    @Schema(description = "전공", example = "컴공")
    private String major;
}
