package sejongZoo.sejongZoo.common.exception.faq;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FaqTitleNotFound extends RuntimeException{
    public FaqTitleNotFound() {
        super();
    }
}
