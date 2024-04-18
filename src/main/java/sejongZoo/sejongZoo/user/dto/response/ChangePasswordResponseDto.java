package sejongZoo.sejongZoo.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
@Builder
public class ChangePasswordResponseDto {
    private String studentId;
    private String nickname;
}
