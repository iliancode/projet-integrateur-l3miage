package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;


@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité mihahoot")
public class CreateMiahootRequest {
    @Schema(description = "id du miahoot", hidden = true)
    @Null
    private Long id;

    @Schema(description = "description de l'objet miahoot", example = "Quizz Web")
    @NotBlank
    private String nom;
}