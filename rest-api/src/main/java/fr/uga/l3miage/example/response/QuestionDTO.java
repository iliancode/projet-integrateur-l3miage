package fr.uga.l3miage.example.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@Schema(description = "correspond au DTO de l'entité Question")
public class QuestionDTO{
    private Long id;

    @NotBlank
    private String label;
    //@NotNull
    //@Size(min=2, message= "Il dot y avoir au moins 2 reponses pour une question donnée")
    List<ReponseDTO> reponses;
}