package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.ParticipantEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.ParticipantNotFoundErrorResponse;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import fr.uga.l3miage.example.response.QuestionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Tag(name = "Miahoot tag")
@CrossOrigin
@RestController
@RequestMapping("miahoots/")
public interface MiahootEndpoint {

    @Operation(description = "Récupérer le DTO de l'entité miahoot qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie le DTO de l'entité miahoot demandée",
            content = @Content(schema = @Schema(implementation = MiahootDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = ParticipantNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    MiahootDTO getEntityMiahoot(@PathVariable("id") Long id);

    @Operation(description = "Création d'une entité Miahoot")
    @ApiResponse(responseCode = "201", description = "L'entité Miahoot a bien été créée.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createEntityMiahoot(@Valid @RequestBody CreateMiahootRequest request);

    @Operation(description = "Suppression d'une entité miahoot en bd")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = ParticipantEntityNotDeletedErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    void deleteMiahootEntity(@PathVariable("id") Long id);

    //miahoots/{id}/questions/{id} get
    //recuperer une question d'un miahoot

    @Operation(description = "Recuperer une question d'un miahoot avec son id et l'id du miahoot")
    @ApiResponse(responseCode = "200", description = "Renvoie la question demandée",
            content = @Content(schema = @Schema(implementation = MiahootDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si la question n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = ParticipantNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{idMiahoot}/questions/{idQuestion}")
    QuestionDTO getQuestion(@PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion) throws Exception;

    //miahoots/{id}/questions/{id} delete
    //supprimer une question d'un miahoot
    @Operation(description = "Supprimer une question d'un miahoot avec son id et l'id du miahoot")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si la question n'a pas pu être supprimée",
            content = @Content(schema = @Schema(implementation = ParticipantNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{idMiahoot}/questions/{idQuestion}")
    void deleteQuestion(@PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion) throws Exception;
}
