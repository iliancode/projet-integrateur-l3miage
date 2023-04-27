package fr.uga.l3miage.example.exception.rest;

import fr.uga.l3miage.example.error.ErrorCode;
import org.springframework.http.HttpStatus;

public class ParticipantEntityNotDeletedRestException extends RuntimeException {
    public ParticipantEntityNotDeletedRestException(String message) {
        super(message);
    }

    public ParticipantEntityNotDeletedRestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getHttpStatus() {return HttpStatus.I_AM_A_TEAPOT;}

    public ErrorCode getErrorCode(){return ErrorCode.TEST_ENTITY_NOT_DELETED_ERROR;}
}
