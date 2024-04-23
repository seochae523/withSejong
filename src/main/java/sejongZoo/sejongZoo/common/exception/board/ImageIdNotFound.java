package sejongZoo.sejongZoo.common.exception.board;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageIdNotFound extends RuntimeException{
    public ImageIdNotFound() {
        super();
    }
}
