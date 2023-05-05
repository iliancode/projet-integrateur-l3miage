package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité partie")
public class CreatePartieRequest {

    @Schema(description = "description d'un entier", example = "1")
    private Long codePartie;

    @Schema(description = "description d'un nom", example = "quizz vache")
    @NotBlank
    private String nom;





}