package fr.uga.l3miage.example.exception.technical.entityNotFoundException;

import lombok.Getter;

@Getter
public class EnseignantEntityNotFoundException extends Exception {
    private final Long id;

    public EnseignantEntityNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public EnseignantEntityNotFoundException(String message, Long id, Throwable cause) {
        super(message, cause);
        this.id = id;
    }


}
