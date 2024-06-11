package sejongZoo.sejongZoo.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sejongZoo.sejongZoo.user.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
    @NotBlank(message = "Name Not Found.")
    @Schema(description = "이름", example = "실명")
    private String name;

    @NotBlank(message = "Student Id Not Found.")
    @Schema(description = "학번", example = "1901914912")
    private String studentId;

    @NotBlank(message = "Password Id Not Found.")
    @Schema(description = "비번", example = "12321")
    private String password;

    @NotBlank(message = "Nickname Not Found.")
    @Schema(description = "별명", example = "정건희 바보")
    private String nickname;

    @NotBlank(message = "Major Not Found.")
	@Schema(description = "전공", example = "컴공")
    private String major;
    public User toEntity(){
        return User.builder()
                .studentId(studentId)
                .password(password)
                .nickname(nickname)
                .name(name)
                .major(major)
                .manner(50)
                .build();
    }
}
