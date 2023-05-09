package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité participant")
public class CreateParticipantRequest {

    @Schema(description = "id de l'objet participant", hidden = true)
    @Null
    private Long id;

    @Schema(description = "pseudo de l'objet participant", example = "Player1")
    @NotBlank
    private String pseudo;
}