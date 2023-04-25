package fr.uga.l3miage.example.response;

import javax.validation.constraints.*;

public class PartieDTO {
    @Min(value = 0)
    private int codePartie;
    @NotNull
    private MiahootDTO miahoot;
}
