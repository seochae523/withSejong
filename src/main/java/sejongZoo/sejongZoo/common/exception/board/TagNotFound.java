package sejongZoo.sejongZoo.common.exception.board;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TagNotFound extends RuntimeException{
    String message;

    public TagNotFound(Long tagId) {
        super(tagId.toString());
        this.message = tagId.toString();
    }
}
