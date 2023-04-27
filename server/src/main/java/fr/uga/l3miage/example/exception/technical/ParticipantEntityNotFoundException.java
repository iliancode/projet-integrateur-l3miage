package fr.uga.l3miage.example.exception.technical;

import lombok.Getter;

@Getter
public class ParticipantEntityNotFoundException extends Exception {
    private final Long id;

    public ParticipantEntityNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public ParticipantEntityNotFoundException(String message, Long id, Throwable cause) {
        super(message, cause);
        this.id = id;
    }


}
