package fr.uga.l3miage.example.request;

import fr.uga.l3miage.example.response.QuestionDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Null;
import java.util.Collection;
import java.util.List;

@Getter
@Data
@Builder
public class CreateFullMiahootRequest {
    @Schema(description = "id du miahoot", hidden = true)
    @Null
    private Long id;

    @Schema(description = "description de l'objet miahoot", example = "Quizz Web")
    private String nom;

    private List<CreateQuestionWithReponseRequest> questions;

}
