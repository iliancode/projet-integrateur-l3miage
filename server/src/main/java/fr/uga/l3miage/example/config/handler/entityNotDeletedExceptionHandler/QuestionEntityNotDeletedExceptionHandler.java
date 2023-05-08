package fr.uga.l3miage.example.config.handler.entityNotDeletedExceptionHandler;

import fr.uga.l3miage.example.error.entityNotDeletedErrorResponse.QuestionEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.errorResponse.ErrorResponse;
import fr.uga.l3miage.example.error.entityNotDeletedErrorResponse.ReponseEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.errorResponse.QuestionErrorResponse;
import fr.uga.l3miage.example.error.errorResponse.ReponseErrorResponse;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.QuestionEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.ReponseEntityNotDeletedRestException;
import fr.uga.l3miage.example.models.Reponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ConditionalOnWebApplication
@RequiredArgsConstructor
@ControllerAdvice
@Slf4j
public class QuestionEntityNotDeletedExceptionHandler {
    @ExceptionHandler(QuestionEntityNotDeletedRestException.class)
    public ResponseEntity<QuestionErrorResponse> handle(HttpServletRequest httpServletRequest, Exception exception){
        QuestionEntityNotDeletedRestException ex = (QuestionEntityNotDeletedRestException) exception;
        final QuestionEntityNotDeletedErrorResponse response = QuestionEntityNotDeletedErrorResponse.builder()
                .uri(httpServletRequest.getRequestURI())
                .httpStatus(ex.getHttpStatus())
                .errorCode(ex.getErrorCode())
                .errorMessage(ex.getMessage())
                .build();
        log.warn(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }
}
