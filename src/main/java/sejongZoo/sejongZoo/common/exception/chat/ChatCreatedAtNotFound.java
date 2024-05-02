package sejongZoo.sejongZoo.common.exception.chat;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class ChatCreatedAtNotFound extends RuntimeException{
    public ChatCreatedAtNotFound() {
        super();
    }
}
