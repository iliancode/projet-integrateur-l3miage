package fr.uga.l3miage.example.exception.technical.entityNotFoundException;

import lombok.Getter;
import fr.uga.l3miage.example.error.ErrorCode;
import org.springframework.http.HttpStatus;


@Getter
public class ReponseEntityNotFoundException extends Exception {

    private final Long id;
    public ReponseEntityNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public ReponseEntityNotFoundException(String message, Long id, Throwable cause){
        super(message,cause);
        this.id = id;
    }
}
