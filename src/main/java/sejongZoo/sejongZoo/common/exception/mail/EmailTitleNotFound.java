package sejongZoo.sejongZoo.common.exception.mail;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailTitleNotFound extends RuntimeException{
    public EmailTitleNotFound() {
        super();
    }
}
