package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.entityNotDeletedErrorResponse.ReponseEntityNotDeletedErrorResponse;
import fr.uga.l3miage.example.error.notFoundErrorResponse.ReponseNotFoundErrorResponse;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Tag(name = "Reponse tag")
@CrossOrigin
@RestController
@RequestMapping("api/reponses/")
public interface ReponseEndpoint{

    //recupererer le DTO de l'entité reponse
    @Operation(description = "Récupérer le DTO de l'entité reponse qui a pour id celui passé en paramètre")
    @ApiResponse(responseCode = "200", description = "Renvoie le DTO de l'entité reponse demandée",
            content = @Content(schema = @Schema(implementation = ReponseDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = ReponseNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    ReponseDTO getEntityReponse(@PathVariable String label);


    //Creation de l'entité Reponse
    @Operation(description = "Création d'une entité Reponse")
    @ApiResponse(responseCode = "201", description = "L'entité Reponse a bien été créée.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createEntityReponse(@Valid @RequestBody CreateReponseRequest request);


    //mettre à jour l'entité Reponse
    @Operation(description = "Mise à jour d'une entité reponse")
    @ApiResponse(responseCode = "202", description = "L'entité reponse à bien été mis à jour")
    @ApiResponse(responseCode = "404", description = "l'entité reponse n'est pas trouvée",
            content = @Content(schema = @Schema(implementation = ReponseNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @Error400Custom
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("{id}")
    void updateReponseEntity(@PathVariable final Integer id,@RequestBody final ReponseDTO reponse);

    //Supprimer l'entité reponse
    @Operation(description = "Suppression d'une entité Reponse en bd")
    @ApiResponse(responseCode = "200", description = "l'entité a bien été supprimée")
    @ApiResponse(responseCode = "418", description = " l'entité n'a pas pu être supprimée",
            content = @Content(schema = @Schema(implementation = ReponseEntityNotDeletedErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    void deleteReponseEntity(@PathVariable String label);
}
