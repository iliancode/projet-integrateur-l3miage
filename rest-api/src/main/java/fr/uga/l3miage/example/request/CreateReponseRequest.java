package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Correspond à la requête permettant de créer une entité reponse")
public class CreateReponseRequest {

    @Schema(description = "description de l'objet reponse", example = "description reponse")
    private String label;

    @Schema(description = "description d'un bool", example = "false")
    private Boolean estValide;
}