package fr.uga.l3miage.example.exception.rest.entityNotFoundRestException;

import fr.uga.l3miage.example.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EnseignantEntityNotFoundRestException extends RuntimeException {
    private final String uid;

    public EnseignantEntityNotFoundRestException(String message, String uid) {
        super(message);
        this.uid = uid;
    }

    public EnseignantEntityNotFoundRestException(String message, String uid, Throwable cause) {
        super(message, cause);
        this.uid = uid;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public ErrorCode getErrorCode(){return ErrorCode.ENSEIGNANT_IS_NOT_FOUND;}
}
