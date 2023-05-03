package fr.uga.l3miage.example.exception.technical.isNotEntityOf;

import lombok.Getter;

@Getter
public class IsNotPartieOfEnseignantException extends Exception {
    private final String mail;
    private final Long codePartie;

    public IsNotPartieOfEnseignantException(String message, String mail, Long codePartie) {
        super(message);
        this.mail = mail;
        this.codePartie = codePartie;
    }

    public IsNotPartieOfEnseignantException(String message, String mail, Long codePartie, Throwable cause) {
        super(message, cause);
        this.mail = mail;
        this.codePartie = codePartie;
    }

}
