package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité mihahoot")
public class CreateMiahootRequest {
    @Schema(description = "id du miahoot", example = "1")
    private Long id;

    @Schema(description = "description de l'objet miahoot", example = "description miahoot")
    private String nom;
}