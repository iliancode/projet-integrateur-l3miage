package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Miahoot")
public class MiahootDTO{
    @NotBlank(message="Le nom du miahoot est obligatoire")
    String nom;
    //ajouter notNull.list ?
    Collection<QuestionDTO> questions;
}