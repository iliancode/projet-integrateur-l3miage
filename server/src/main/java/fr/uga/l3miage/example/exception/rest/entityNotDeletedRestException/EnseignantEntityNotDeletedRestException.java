package fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException;

import fr.uga.l3miage.example.error.ErrorCode;
import org.springframework.http.HttpStatus;

public class EnseignantEntityNotDeletedRestException extends RuntimeException {
    public EnseignantEntityNotDeletedRestException(String message) {
        super(message);
    }

    public EnseignantEntityNotDeletedRestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getHttpStatus() {return HttpStatus.NOT_FOUND;}

    public ErrorCode getErrorCode(){return ErrorCode.ENSEIGNANT_ENTITY_NOT_DELETED_ERROR;}
}

