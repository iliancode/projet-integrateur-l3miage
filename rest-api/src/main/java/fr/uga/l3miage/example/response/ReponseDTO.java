package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@Schema(description = "correspond au DTO de l'entité Reponse")
public class ReponseDTO{

        @Schema(description = "id de la question", example = "1")
        private Long id;

        @Schema(description = "description de l'objet reponse", example = "Un identifiant unique pour une page web")
        @NotBlank(message = "L'énonciation des réponses est obligatoire")
        private String label;

        @Schema(description = "description d'un bool", example = "true")
        @NotNull
        private Boolean estValide;
}