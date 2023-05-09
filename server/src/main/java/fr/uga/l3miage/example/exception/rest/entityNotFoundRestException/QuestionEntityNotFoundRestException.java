package fr.uga.l3miage.example.exception.rest.entityNotFoundRestException;

import fr.uga.l3miage.example.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Correspond à l'exception d'API d'une entité non trouvée<br>
 */

@Getter
public class QuestionEntityNotFoundRestException extends RuntimeException {
    private final Long id;

    public QuestionEntityNotFoundRestException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public QuestionEntityNotFoundRestException(String message, Long id, Throwable cause){
        super(message,cause);
        this.id = id;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
    public ErrorCode getErrorCode(){return ErrorCode.QUESTION_IS_NOT_FOUND;}
}
