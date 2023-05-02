package fr.uga.l3miage.example.endpoint;


import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.notFoundErrorResponse.PartieNotFoundErrorResponse;
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
import java.util.List;

@Tag(name = "Partie tag")
@CrossOrigin
@RestController
@RequestMapping("parties/")
public interface PartieEndpoint {

    @Operation(description = "Création d'une entité Participant dans la Partie du codePartie passé en paramètre")
    @ApiResponse(responseCode = "201", description = "L'entité Participant de la Partie avec le codePartie demandé a bien été créée.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{codePartie}/participants")
    void createParticipantOfPartie(@PathVariable("codePartie") Long codePartie, @Valid @RequestBody CreateParticipantRequest request);

    @Operation(description = "Récupérer la liste de DTO des entités participants de la partie qui a pour codePartie celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie la liste de DTO des entités participants de la partie demandée",
            content = @Content(schema = @Schema(implementation = ParticipantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité partie demandée n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = PartieNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{codePartie}/participants")
    List<ParticipantDTO> getAllParticipantsByPartie(@PathVariable("codePartie") Long codePartie);
}