package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Schema(description = "correspond au DTO de l'entité Partie")
public class PartieDTO {
    @Min(value = 0)
    private long codePartie;
    @NotNull(message="Une partie doit correspondre a un miahoot")
    private MiahootDTO miahoot;
}
