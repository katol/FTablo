package FTablo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchFightException extends RuntimeException {
    public NoSuchFightException(Integer id) {
        super("Fight with id " + id + " does not exist");
    }
}
