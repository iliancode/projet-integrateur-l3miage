package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
import fr.uga.l3miage.example.response.ReponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints des Réponses")
@CrossOrigin
@RestController
@RequestMapping("api/")
public interface ReponseEndpoint {

    @Operation(description = "Ajout d'une reponse à une question d'un miahoot d'un enseignant")
    @ApiResponse(responseCode = "202", description = "la reponse a bien été ajoutée")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("enseignants/{idEnseignant}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses")
    void addReponseToQuestionOfMiahoot(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion, @RequestBody CreateReponseRequest createReponseRequest) throws Exception;


    @Operation(description = "Récupération de toutes les reponses d'une question d'un miahoot d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités reponse",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("enseignants/{idEnseignant}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses")
    List<ReponseDTO> getAllReponsesOfQuestionOfMiahootOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion) throws Exception;


    @Operation(description = "recupere la reponse avec l'id correspondant dans la liste de reponse de la question du miahoot de l'enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une entité reponse avec l'id correspondant a  celui passé en parametre",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("enseignants/{idEnseignant}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses/{idReponse}")
    ReponseDTO getReponseOfQuestionOfMiahootOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion, @PathVariable("idReponse") Long idReponse);


    @Operation(description = "Suppression d'une entité enseignant en bd")
    @ApiResponse(responseCode = "200", description = "si  l'element est renvoyé et supprimé")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("enseignants/{idEnseignant}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses/{idReponse}")
    void deleteReponseOfQuestionOfMiahootOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion, @PathVariable("idReponse") Long idReponse) throws Exception;
}
