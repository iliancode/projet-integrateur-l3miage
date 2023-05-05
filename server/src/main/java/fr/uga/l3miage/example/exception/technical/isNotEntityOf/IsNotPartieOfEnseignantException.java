package fr.uga.l3miage.example.exception.technical.isNotEntityOf;

import lombok.Getter;

@Getter
public class IsNotPartieOfEnseignantException extends Exception {
    private final Long idEnseignant;
    private final Long codePartie;

    public IsNotPartieOfEnseignantException(String message, Long idEnseignant, Long codePartie) {
        super(message);
        this.idEnseignant = idEnseignant;
        this.codePartie = codePartie;
    }

    public IsNotPartieOfEnseignantException(String message, Long idEnseignant, Long codePartie, Throwable cause) {
        super(message, cause);
        this.idEnseignant = idEnseignant;
        this.codePartie = codePartie;
    }

}
