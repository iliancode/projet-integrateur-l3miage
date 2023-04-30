package fr.uga.l3miage.example.exception.technical.alreadyExistException;

import lombok.Getter;

/**
 * Exception technique<br>
 * Les annotations :
 * <ul>
 *     <li>{@link Getter} permet de créer tout les getters de tous les attributs. Voir la doc <a href="https://projectlombok.org/features/GetterSetter">projetlombok.org/features/Getter</a></li>
 * </ul>
 */
@Getter
public class MailAlreadyExistException extends Exception {
    private final String mail;

    public MailAlreadyExistException(String message, String mail) {
        super(message);
        this.mail = mail;
    }

    public MailAlreadyExistException(String message, String mail, Throwable cause) {
        super(message, cause);
        this.mail = mail;
    }
}
