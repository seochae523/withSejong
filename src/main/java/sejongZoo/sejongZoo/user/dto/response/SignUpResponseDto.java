package sejongZoo.sejongZoo.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpResponseDto {
    private String name;
    private String studentId;

    @Builder
    public SignUpResponseDto(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }
}
