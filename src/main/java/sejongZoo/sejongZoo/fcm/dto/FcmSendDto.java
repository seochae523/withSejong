package sejongZoo.sejongZoo.fcm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FcmSendDto {
    @Schema(description = "프론트에서 발급받은 기기 식별용 토큰", example = "something-token-code-in-here")
    private String token;
    @Schema(description = "알림 제목", example = "something title")
    private String title;
    @Schema(description = "알림 내용", example = "something content")
    private String body;
}
