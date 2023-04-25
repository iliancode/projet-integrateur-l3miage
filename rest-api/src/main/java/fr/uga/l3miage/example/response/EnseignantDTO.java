package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;


@Schema(description = "correspond au DTO de l'entité Enseignant")
public class EnseignantDTO {
    @Email
    @NotBlank
    private String mail;
    @NotBlank
    private String mdp;
    @NotBlank
    private String pseudo;

    @Null
    private List<MiahootDTO> miahoots;

    @Null
    private List<PartieDTO> parties;
}
