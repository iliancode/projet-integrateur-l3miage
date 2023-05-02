package fr.uga.l3miage.example.exception.technical.entityNotFoundException;

import lombok.Getter;

@Getter
public class PartieEntityNotFoundException extends Exception {
    private final Long id;

    public PartieEntityNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public PartieEntityNotFoundException(String message, Long id, Throwable cause) {
        super(message, cause);
        this.id = id;
    }


}
