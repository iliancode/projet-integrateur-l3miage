package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.Collection;


@Data
@Builder
@Schema(description = "correspond au DTO de l'entité Presentateur")
public class PresentateurDTO {
    @Email
    @NotBlank
    private String mail;
    @NotBlank
    private String mdp;
    @NotBlank
    private String pseudo;

    @Null
    private Collection<MiahootDTO> miahoots;

    @Null
    private Collection<PartieDTO> parties;
}
