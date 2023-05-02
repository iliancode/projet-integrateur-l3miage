package fr.uga.l3miage.example.exception.rest.entityNotFoundRestException;

import fr.uga.l3miage.example.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PartieEntityNotFoundRestException extends RuntimeException {
    private final Long codePartie;

    public PartieEntityNotFoundRestException(String message, Long codePartie) {
        super(message);
        this.codePartie = codePartie;
    }

    public PartieEntityNotFoundRestException(String message, Long codePartie, Throwable cause) {
        super(message, cause);
        this.codePartie = codePartie;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    public ErrorCode getErrorCode(){return ErrorCode.PARTIE_IS_NOT_FOUND;}
}
