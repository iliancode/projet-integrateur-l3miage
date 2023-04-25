package fr.uga.l3miage.example.response;

import javax.validation.constraints.*;

public class ParticipantDTO {
    @NotBlank
    private String pseudo;
}
