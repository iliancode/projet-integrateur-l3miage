package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.request.CreateFullMiahootRequest;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
import fr.uga.l3miage.example.response.MiahootDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints des Miahoots")
@CrossOrigin
@RestController
@RequestMapping("api/")
public interface MiahootEndpoint {

    @Operation(description = "Création d'une entité miahoot et ajout dans la liste des miahoot de l'enseignant")
    //@ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si le miahoot na pas pu etre creer",
    //      content = @Content(schema = @Schema(implementation = TestNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "201", description = "L'entité Miahoot a bien été créée et ajoutée à la liste de Miahoots de l'enseignant.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{uidEnseignant}/miahoots")
    public void createEntityMiahootFromEnseignant(@PathVariable("uidEnseignant") String uidEnseignant, @RequestBody CreateMiahootRequest request) throws Exception;


    @Operation(description = "Récupération de tous les miahoots d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités miahoot",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("enseignants/{uidEnseignant}/miahoots")
    List<MiahootDTO> getAllMiahootsOfEnseignant(@PathVariable("uidEnseignant") String uidEnseignant) throws Exception;


    @Operation(description = "recupere le miahoot avec l'id correspondant dans la liste de miahoot de l'enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une entité miahoot avec l'id correspondant a  celui passé en parametre",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("enseignants/{uidEnseignant}/miahoots/{idMiahoot}")
    MiahootDTO getMiahootOfEnseignant(@PathVariable("uidEnseignant") String uidEnseignant, @PathVariable("idMiahoot") Long idMiahoot) throws Exception;


    @Operation(description = "Suppression d'une entité miahoot d'un enseignant en bd")
    @ApiResponse(responseCode = "200", description = "si  l'element est renvoyé et supprimé")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("enseignants/{idEnseignant}/miahoots/{idMiahoot}")
    void deleteMiahootOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot);


    @Operation(description = "Récupération d'un miahoot et de toutes ses questions et de toutes ses reponses d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Créer un miahoot entier",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("enseignants/{uidEnseignant}/miahoot")
    void createMiahootOfEnseignant(@PathVariable("uidEnseignant") String uidEnseignant, @RequestBody CreateFullMiahootRequest createFullMiahootRequest) throws Exception;

}
