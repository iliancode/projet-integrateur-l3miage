package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@Schema(description = "Correspond à la requête permettant de créer une entité question avec ses réponses")
public class CreateQuestionWithReponseRequest {

    @Schema(description = "id de l'objet question", hidden = true)
    @Null
    private Long id;

    @Schema(description = "description de l'objet question", example = "Qu'est-ce qu'un \"URL\" ?")
    private String label;

    private List<CreateReponseRequest> reponses;

}