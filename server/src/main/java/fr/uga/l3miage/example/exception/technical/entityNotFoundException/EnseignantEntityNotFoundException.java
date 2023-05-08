package fr.uga.l3miage.example.exception.technical.entityNotFoundException;

import lombok.Getter;

@Getter
public class EnseignantEntityNotFoundException extends Exception {
    private final String id;

    public EnseignantEntityNotFoundException(String message, String id) {
        super(message);
        this.id = id;
    }

    public EnseignantEntityNotFoundException(String message, String id, Throwable cause) {
        super(message, cause);
        this.id = id;
    }


}
