package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Miahoot")
public class MiahootDTO{
    @NotBlank(message="Le nom du miahoot est obligatoire")
    String nom;

    @NotNull
    @Size(min=1, message= "Il dot y avoir au moins une question dans le QCM ")
    Collection<QuestionDTO> questions;
}