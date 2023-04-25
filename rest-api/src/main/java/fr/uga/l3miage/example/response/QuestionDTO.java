package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Schema(description = "correspond au DTO de l'entité Question")
public class QuestionDTO{
    @NotBlank(message ="La formulation d'une question est obligatoire")
    String label;
    @NotNull
    @Size(min=2, message= "Il dot y avoir au moins 2 reponses pour une question donnée")
    Collection<ReponseDTO> reponses;
}