package sejongZoo.sejongZoo.common.exception.board;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class BoardCreatedAtNotFound extends RuntimeException{
    public BoardCreatedAtNotFound() {
        super();
    }
}
