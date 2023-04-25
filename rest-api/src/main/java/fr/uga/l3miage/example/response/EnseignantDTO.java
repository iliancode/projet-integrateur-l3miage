package fr.uga.l3miage.example.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.Collection;

public class EnseignantDTO {
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
