package sejongZoo.sejongZoo.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CheckStudentIdResponseDto {
    private Boolean isSigned;
    private Boolean isDeleted;
}
