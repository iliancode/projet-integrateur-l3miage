package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité participant")
public class CreateParticipantRequest {

    @Schema(description = "id de l'objet participant", example = "1")
    private Long id;
    @Schema(description = "pseudo de l'objet participant", example = "description participant")
    private String pseudo;

    /*@Schema(description = "partie de l'objet participant", example = "partie participant")
    private PartieDTO Partie;*/
}