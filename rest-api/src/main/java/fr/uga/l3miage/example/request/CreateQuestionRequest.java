package fr.uga.l3miage.example.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@Schema(description = "Correspond à la requête permettant de créer une entité question")
public class CreateQuestionRequest {

    @Schema(description = "id de l'objet question", example = "1")
    private Long id;

    @Schema(description = "description de l'objet question", example = "description question ? ")
    private String label;

}