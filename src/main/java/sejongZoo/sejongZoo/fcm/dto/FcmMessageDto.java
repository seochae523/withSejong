package sejongZoo.sejongZoo.fcm.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FcmMessageDto {
    @Schema(description = "테스트 버전이냐 아니냐 true = 테스트 버전, false = 배포 버전", example = "false")
    private Boolean validateOnly;
    @Schema(description = "메시지", example = "something happend")
    private FcmMessageDto.Message message;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message {
        private FcmMessageDto.Notification notification;
        private String token;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }
}
