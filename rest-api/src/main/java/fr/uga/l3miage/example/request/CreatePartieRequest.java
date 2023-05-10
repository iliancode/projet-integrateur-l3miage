package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité partie")
public class CreatePartieRequest {

    @Schema(description = "id partie", hidden = true)
    @Null
    private Long id;

    @Schema(description = "code partie", example = "31241341")
    @NotBlank
    private Long codePartie;

    @Schema(description = "description d'un nom", example = "Quizz Web L3 MIAGE groupe 1")
    @NotBlank
    private String nom;





}