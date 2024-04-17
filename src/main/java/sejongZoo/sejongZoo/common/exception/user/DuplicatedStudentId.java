package sejongZoo.sejongZoo.common.exception.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedStudentId extends RuntimeException{
    String message;
    public DuplicatedStudentId(String studentId) {
        super(studentId);
        this.message = studentId;
    }
}
