package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Reponse")
public  class ReponseDTO{
        @NotBlank(message = "L'énonciation des réponses est obligatoire")
        String label;
        Boolean estValide;

}