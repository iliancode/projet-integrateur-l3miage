package fr.uga.l3miage.example.exception.technical.isNotEntityOf;

import lombok.Getter;

@Getter
public class IsNotPartieOfEnseignantException extends Exception {
    private final String uidEnseignant;
    private final Long codePartie;

    public IsNotPartieOfEnseignantException(String message, String uidEnseignant, Long codePartie) {
        super(message);
        this.uidEnseignant = uidEnseignant;
        this.codePartie = codePartie;
    }

    public IsNotPartieOfEnseignantException(String message, String uidEnseignant, Long codePartie, Throwable cause) {
        super(message, cause);
        this.uidEnseignant = uidEnseignant;
        this.codePartie = codePartie;
    }

}
