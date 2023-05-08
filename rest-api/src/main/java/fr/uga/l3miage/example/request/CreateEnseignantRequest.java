package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité participant")
public class CreateEnseignantRequest {

    @Schema(description = "id de l'objet participant", example = "TUGY23436GERFERG")
    @NotBlank
    private String uid;

    @Schema(description = "description de l'objet participant", example = "enseignant1")
    @NotBlank
    private String pseudo;

    @Schema(description = "description d'un champs e-mail participant", example = "enseignant1@gmail.com")
    @Email
    @NotBlank
    private String mail;

    @Schema(description = "description d'un champs mot de passe participant", example = "AzerTy123")
    @NotBlank
    private String mdp;
}