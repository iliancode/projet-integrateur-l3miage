package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité participant")
public class CreateEnseignantRequest {

    @Schema(description = "description de l'objet participant", example = "description participant")
    private String pseudo;

    @Schema(description = "description d'un champs e-mail participant", example = "paticipant@gmail.com")
    private String mail;

    @Schema(description = "description d'un champs mot de passe participant", example = "AzerTy123")
    private String mdp;
}