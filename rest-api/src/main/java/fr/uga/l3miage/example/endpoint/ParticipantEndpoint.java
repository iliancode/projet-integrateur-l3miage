package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.ParticipantEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.ParticipantNotFoundErrorResponse;
import fr.uga.l3miage.example.request.CreateParticipantRequest;
import fr.uga.l3miage.example.response.ParticipantDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Participant tag")
@CrossOrigin
@RestController
@RequestMapping("participants/")
public interface ParticipantEndpoint {

    @Operation(description = "Récupérer le DTO de l'entité participant qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie le DTO de l'entité participant demandée",
            content = @Content(schema = @Schema(implementation = ParticipantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = ParticipantNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    ParticipantDTO getEntityParticipant(@PathVariable("id") Long id);


    @Operation(description = "Création d'une entité Participant")
    @ApiResponse(responseCode = "201", description = "L'entité Participant a bien été créée.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createEntityParticipant(@Valid @RequestBody CreateParticipantRequest request);


    @Operation(description = "Suppression d'une entité participant en bd")
    @ApiResponse(responseCode = "200", description = "si isInError est à false alors l'element est renvoyé et supprimé")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = ParticipantEntityNotDeletedErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    void deleteParticipantEntity(@PathVariable("id") Long id);

}