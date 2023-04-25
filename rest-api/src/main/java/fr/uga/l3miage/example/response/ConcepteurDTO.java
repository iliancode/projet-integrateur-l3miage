package fr.uga.l3miage.example.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ConcepteurDTO {
    @Email
    @NotBlank
    private String mail;
    @NotBlank
    private String mdp;
    @NotBlank
    private String pseudo;
}
