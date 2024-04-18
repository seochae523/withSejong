package sejongZoo.sejongZoo.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ChangePasswordRequestDto {
    private String studentId;
    private String password;
}
