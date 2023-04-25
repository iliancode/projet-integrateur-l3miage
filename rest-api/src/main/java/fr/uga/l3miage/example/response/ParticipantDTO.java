package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "correspond au DTO de l'entité Participant")
public class ParticipantDTO {
    private String pseudo;

    @NotNull
    private PartieDTO partie;
}
