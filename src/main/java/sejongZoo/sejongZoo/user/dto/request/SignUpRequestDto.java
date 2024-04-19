package sejongZoo.sejongZoo.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sejongZoo.sejongZoo.user.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
    @Schema(description = "이름", example = "실명")
    private String name;
    @Schema(description = "학번", example = "1901914912")
    private String studentId;
    @Schema(description = "비번", example = "12321")
    private String password;
    @Schema(description = "별명", example = "정건희 바보")
    private String nickname;
	@Schema(description = "전공", example = "컴공")
    private String major;
    @Schema(description = "가입 여부", example = "false")
    private Boolean isSigned;
    @Schema(description = "삭제 여부", example = "false")
    private Boolean isDeleted;
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
