package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@Schema(description = "correspond au DTO de l'entité Reponse")
public  class ReponseDTO{
        @NotBlank(message = "L'énonciation des réponses est obligatoire")
        private String label;
        @NotBlank(message = "La validité de la réponse est obligatoire")
        private Boolean estValide;

}