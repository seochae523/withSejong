package sejongZoo.sejongZoo.common.exception.board;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageNotFound extends RuntimeException{
    String message;
    public ImageNotFound(Long id) {
        super();
        this.message = id.toString();
    }
}
