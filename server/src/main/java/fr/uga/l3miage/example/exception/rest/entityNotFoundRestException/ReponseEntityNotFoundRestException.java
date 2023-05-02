package fr.uga.l3miage.example.exception.rest.entityNotFoundRestException;

import fr.uga.l3miage.example.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Correspond à l'exception d'API d'une entité non trouvée<br>
 */

@Getter
public class ReponseEntityNotFoundRestException extends RuntimeException {
    private final String label;

    public ReponseEntityNotFoundRestException(String message, String label) {
        super(message);
        this.label = label;
    }

    public ReponseEntityNotFoundRestException(String message, String label, Throwable cause){
        super(message,cause);
        this.label = label;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
    public ErrorCode getErrorCode(){return ErrorCode.TEST_IS_NOT_FOUND;}
}