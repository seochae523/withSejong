package sejongZoo.sejongZoo.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sejongZoo.sejongZoo.user.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
    private String name;
    private String studentId;
    private String password;
    private String nickname;

    public User toEntity(){
        return User.builder()
                .studentId(studentId)
                .password(password)
                .nickname(nickname)
                .name(name)
                .manner(50)
                .build();
    }
}
