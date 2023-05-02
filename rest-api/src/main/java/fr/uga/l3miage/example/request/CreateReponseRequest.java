package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@Getter
@Setter
@Schema(description = "Correspond à la requête permettant de créer une entité reponse")
public class CreateReponseRequest {

    @Schema(description = "id de l'objet reponse", example = "1")
    @NotBlank
    private Long id;

    @Schema(description = "description de l'objet reponse", example = "description reponse")
    @NotBlank
    private String label;

    @Schema(description = "description d'un bool", example = "false")
    @NotBlank
    private Boolean estValide;
}