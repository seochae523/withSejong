package sejongZoo.sejongZoo.common.exception.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidAccount extends RuntimeException{
    String message;

    public InvalidAccount(String studentId) {
        super(studentId);
        this.message = studentId;
    }
}
