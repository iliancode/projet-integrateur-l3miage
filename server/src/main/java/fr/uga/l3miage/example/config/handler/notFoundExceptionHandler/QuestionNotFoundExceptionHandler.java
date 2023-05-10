package fr.uga.l3miage.example.config.handler.notFoundExceptionHandler;

import fr.uga.l3miage.example.error.errorResponse.ErrorResponse;
import fr.uga.l3miage.example.error.errorResponse.QuestionErrorResponse;
import fr.uga.l3miage.example.error.notFoundErrorResponse.QuestionNotFoundErrorResponse;
import fr.uga.l3miage.example.error.notFoundErrorResponse.ReponseNotFoundErrorResponse;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.QuestionEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.ReponseEntityNotFoundRestException;
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
public class QuestionNotFoundExceptionHandler {
    @ExceptionHandler(QuestionEntityNotFoundRestException.class)
    public ResponseEntity<QuestionErrorResponse> handle(HttpServletRequest httpServletRequest, Exception exception) {
        QuestionEntityNotFoundRestException ex = (QuestionEntityNotFoundRestException) exception;
        final QuestionNotFoundErrorResponse response = QuestionNotFoundErrorResponse.builder()
                .uri(httpServletRequest.getRequestURI())
                .httpStatus(ex.getHttpStatus())
                .errorMessage(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .id(ex.getId())
                .build();
        log.warn(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }
}
