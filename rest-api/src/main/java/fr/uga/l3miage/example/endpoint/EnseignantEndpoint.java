package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.TestNotFoundErrorResponse;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("enseignants/")
public interface EnseignantEndpoint {
    @Operation(description = "Création d'une entité Enseignant")
    @ApiResponse(responseCode = "201", description = "L'entité Enseignant a bien été créée.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createEntityEnseignant(@RequestBody CreateEnseignantRequest request) throws Exception;

    @Operation(description = "Récupération d'une entité Enseignant par son email")
    @ApiResponse(responseCode = "200", description = "L'entité Enseignant a bien été récupérée.",
    content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_STREAM_JSON_VALUE))
    //@ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
      //      content = @Content(schema = @Schema(implementation = ParticipantNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{mail}")
    EnseignantDTO getEntityEnseignantByMail(@PathVariable String mail) throws Exception;


    //get all enseignants
    @Operation(description = "Récupération de toutes les entités enseignant en bd")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités enseignant",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<EnseignantDTO> getAllEnseignants() throws Exception;


    //delete enseignant by mail
    @Operation(description = "Suppression d'une entité enseignant en bd")
    @ApiResponse(responseCode = "200", description = "si isInError est à false alors l'element est renvoyé et supprimé")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{mail}")
    void deleteEnseignantEntity(@PathVariable("mail") String mail) throws Exception;


    @Operation(description = "Modification d'une entité enseignant en bd")
    @ApiResponse(responseCode = "202", description = "l'entité a bien été modifiée")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être modifiée",
            content = @Content(schema = @Schema(implementation = TestNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @Error400Custom
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("{mail}")
    void updateEnseignantEntity(@PathVariable final String mail, @RequestBody final EnseignantDTO enseignantDTO) throws Exception;


}