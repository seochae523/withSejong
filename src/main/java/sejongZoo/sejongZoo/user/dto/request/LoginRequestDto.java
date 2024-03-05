package sejongZoo.sejongZoo.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {
    private String studentId;
    private String password;

    @Builder
    public LoginRequestDto(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }
}
