package sejongZoo.sejongZoo.fcm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FcmSendDto {
    private String token;
    private String title;
    private String body;
}
