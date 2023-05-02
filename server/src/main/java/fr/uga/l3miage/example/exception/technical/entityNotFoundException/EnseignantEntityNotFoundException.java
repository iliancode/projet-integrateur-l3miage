package fr.uga.l3miage.example.exception.technical.entityNotFoundException;

import lombok.Getter;

@Getter
public class EnseignantEntityNotFoundException extends Exception {
    private final String mail;

    public EnseignantEntityNotFoundException(String message, String mail) {
        super(message);
        this.mail = mail;
    }

    public EnseignantEntityNotFoundException(String message, String mail, Throwable cause) {
        super(message, cause);
        this.mail = mail;
    }


}
