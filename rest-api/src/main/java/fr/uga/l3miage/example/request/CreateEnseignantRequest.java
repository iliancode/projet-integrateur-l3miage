package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@Schema(description = "Correspond à la requête permettant de créer une entité enseignant")
public class CreateEnseignantRequest {

    @Schema(description = "uid de l'objet enseignant", example = "TUGY23436GERFERG")
    @NotBlank
    private String uid;

    @Schema(description = "pseudo de l'objet enseignant", example = "enseignant1")
    @NotBlank
    private String pseudo;

    @Schema(description = "mail d'un champs e-mail enseignant", example = "enseignant1@gmail.com")
    @Email
    @NotBlank
    private String mail;

    @Schema(description = "mot de passe d'un champs mot de passe enseignant", example = "AzerTy123")
    @NotBlank
    private String mdp;
}