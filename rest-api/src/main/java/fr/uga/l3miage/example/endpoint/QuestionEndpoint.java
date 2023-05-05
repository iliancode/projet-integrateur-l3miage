package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
import fr.uga.l3miage.example.response.QuestionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints des Questions")
@CrossOrigin
@RestController
public interface QuestionEndpoint {

    @Operation(description = "Ajout d'une question à un miahoot d'un enseignant")
    @ApiResponse(responseCode = "202", description = "la question a bien été ajoutée")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("enseignants/{idEnseignant}/miahoots/{idMiahoot}/questions")
    void addQuestionToMiahoot(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot, @RequestBody CreateQuestionRequest createQuestionRequest) throws Exception;


    @Operation(description = "Récupération de toutes les questions d'un miahoot d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités question",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("enseignants/{idEnseignant}/miahoots/{idMiahoot}/questions")
    List<QuestionDTO> getAllQuestionsOfMiahootOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot) throws Exception;

}
