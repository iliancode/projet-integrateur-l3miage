package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Correspond à la requête permettant de créer une entité participant")
public class CreateParticipantRequest {

    @Schema(description = "description de l'objet participant", example = "description participant")
    private String pseudo;
}