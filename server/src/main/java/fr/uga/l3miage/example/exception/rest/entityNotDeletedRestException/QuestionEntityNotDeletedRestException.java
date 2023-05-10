package fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException;

import lombok.Getter;
import fr.uga.l3miage.example.error.ErrorCode;
import org.springframework.http.HttpStatus;


@Getter
public class QuestionEntityNotDeletedRestException extends RuntimeException {

    public QuestionEntityNotDeletedRestException(String message) {
        super(message);
    }

    public QuestionEntityNotDeletedRestException(String message, Throwable cause) {super(message, cause); }

    public HttpStatus getHttpStatus() {return HttpStatus.NOT_FOUND;}

    public ErrorCode getErrorCode(){return ErrorCode.QUESTION_ENTITY_NOT_DELETED_ERROR;}
}

