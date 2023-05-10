package fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException;

import lombok.Getter;
import fr.uga.l3miage.example.error.ErrorCode;
import org.springframework.http.HttpStatus;


@Getter
public class ReponseEntityNotDeletedRestException extends RuntimeException {

    public ReponseEntityNotDeletedRestException(String message) {
        super(message);
    }

    public ReponseEntityNotDeletedRestException(String message, Throwable cause) {super(message, cause); }
    public HttpStatus getHttpStatus() {return HttpStatus.NOT_FOUND;}

    public ErrorCode getErrorCode(){return ErrorCode.REPONSE_ENTITY_NOT_DELETED_ERROR;}
}

