package sejongZoo.sejongZoo.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sejongZoo.sejongZoo.user.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
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
                .build();
    }

    public UserDto(User user){
        this.name = user.getName();
        this.studentId = user.getStudentId();
        this.nickname = user.getNickname();
    }
}
