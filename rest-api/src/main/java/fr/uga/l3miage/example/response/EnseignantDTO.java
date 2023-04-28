package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Schema(description = "correspond au DTO de l'entité Enseignant")
public class EnseignantDTO {
    @Email
    @NotBlank
    private String mail;
    @NotBlank
    private String mdp;
    private String pseudo;

    // @Null
    List<MiahootDTO> miahoots;

/*    @Null*/
    List<PartieDTO> parties;
}
