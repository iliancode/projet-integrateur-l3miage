package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Question")
public class QuestionDTO{
    @NotBlank(message ="La formulation d'une question est obligatoire")
    String label;
    //ajouter @notNull.list ?
    Collection<ReponseDTO> reponses;
}