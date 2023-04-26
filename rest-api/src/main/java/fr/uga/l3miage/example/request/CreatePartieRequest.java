package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Correspond à la requête permettant de créer une entité partie")
public class CreatePartieRequest {

    @Schema(description = "description d'un entier", example = "12")
    private int codePartie;
}