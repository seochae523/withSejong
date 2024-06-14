package sejongZoo.sejongZoo.common.exception.chat;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChatNotFound extends RuntimeException{
    String message;
    public ChatNotFound(Long roomId) {
        super(roomId.toString());
        this.message = roomId.toString();
    }
}
