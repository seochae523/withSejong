package sejongZoo.sejongZoo.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;
import sejongZoo.sejongZoo.user.domain.User;

@Getter
@AllArgsConstructor
@Builder
@Setter
public class LogoutResponseDto {
    @Schema(description = "학번", example = "123123")
    private String studentId;
    @Schema(description = "이름", example = "name")
    private String name;
    @Schema(description = "닉네임", example = "nickname")
    private String nickname;
    @Schema(description = "전공", example = "컴공")
    private String major;

    public LogoutResponseDto(User user){
        this.studentId = user.getStudentId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.major = user.getMajor();
    }
}
