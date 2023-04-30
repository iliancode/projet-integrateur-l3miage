package fr.uga.l3miage.example.exception.rest.alreadyUseRestException;

import fr.uga.l3miage.example.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * Correspond à l'exception d'API d'un mail déjà utilisé<br>
 * Les annotations :
 * <ul>
 *     <li>{@link Getter} permet de créer tout les getters de tous les attributs. Voir la doc <a href="https://projectlombok.org/features/GetterSetter">projetlombok.org/features/Getter</a></li>
 * </ul>
 */
@Getter
public class MailAlreadyUseRestException extends RuntimeException {
    private final String mail;

    public MailAlreadyUseRestException(String message, String mail) {
        super(message);
        this.mail = mail;
    }

    public MailAlreadyUseRestException(String message, String mail, Throwable cause) {
        super(message, cause);
        this.mail = mail;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    public ErrorCode getErrorCode(){return ErrorCode.MAIL_ALREADY_USE_ERROR;}
}
