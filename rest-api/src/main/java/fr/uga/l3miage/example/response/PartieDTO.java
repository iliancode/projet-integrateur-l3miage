package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;


@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Partie")
public class PartieDTO {
    @Min(value = 0)
    private int codePartie;
    @NotNull(message="Une partie doit correspondre a un miahoot")
    private MiahootDTO miahoot;
}
