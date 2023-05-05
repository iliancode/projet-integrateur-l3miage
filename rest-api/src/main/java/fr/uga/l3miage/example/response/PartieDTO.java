package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@Schema(description = "correspond au DTO de l'entité Partie")
public class PartieDTO {
    private Long codePartie;

    @NotBlank
    private String nom;


    private MiahootDTO miahoot;

}
