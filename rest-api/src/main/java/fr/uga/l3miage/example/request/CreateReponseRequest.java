package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
@Getter
@Setter
@Schema(description = "Correspond à la requête permettant de créer une entité reponse")
public class CreateReponseRequest {

    @Schema(description = "id de l'objet reponse", hidden = true)
    @Null
    private Long id;

    @Schema(description = "description de l'objet reponse", example = "Un identifiant unique pour une page web")
    @NotBlank
    private String label;

    @Schema(description = "description d'un bool", example = "true")
    @NotNull
    private Boolean estValide;
}