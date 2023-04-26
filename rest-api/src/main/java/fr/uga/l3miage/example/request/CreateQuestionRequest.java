package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Correspond à la requête permettant de créer une entité question")
public class CreateQuestionRequest {

    @Schema(description = "description de l'objet question", example = "description question ? ")
    private String pseudo;

}