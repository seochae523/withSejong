package sejongZoo.sejongZoo.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sejongZoo.sejongZoo.common.token.dto.AuthToken;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponseDto {
    private String studentId;
    private String nickname;
    private String major;
    private AuthToken authToken;
}
