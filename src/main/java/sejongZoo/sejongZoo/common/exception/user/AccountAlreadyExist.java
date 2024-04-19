package sejongZoo.sejongZoo.common.exception.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class AccountAlreadyExist extends RuntimeException{
    String message;

    public AccountAlreadyExist(String studentId) {
        this.message = studentId;
    }
}
