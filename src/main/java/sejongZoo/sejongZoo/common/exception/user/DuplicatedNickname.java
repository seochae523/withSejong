package sejongZoo.sejongZoo.common.exception.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedNickname extends RuntimeException{
    String message;
    public DuplicatedNickname(String nickName) {
        super(nickName);
        this.message = nickName;
    }
}
