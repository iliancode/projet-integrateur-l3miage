package fr.uga.l3miage.example.exception.rest.entityNotFoundRestException;

import fr.uga.l3miage.example.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EnseignantEntityNotFoundRestException extends RuntimeException {
    private final String mail;

    public EnseignantEntityNotFoundRestException(String message, String mail) {
        super(message);
        this.mail = mail;
    }

    public EnseignantEntityNotFoundRestException(String message, String mail, Throwable cause) {
        super(message, cause);
        this.mail = mail;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public ErrorCode getErrorCode(){return ErrorCode.ENSEIGNANT_IS_NOT_FOUND;}
}
