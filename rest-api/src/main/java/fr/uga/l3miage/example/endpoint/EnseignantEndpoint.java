package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.notFoundErrorResponse.EnseignantNotFoundErrorResponse;
import fr.uga.l3miage.example.error.notFoundErrorResponse.TestNotFoundErrorResponse;
import fr.uga.l3miage.example.request.*;
import fr.uga.l3miage.example.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Endpoints des Enseignants")
@CrossOrigin
@RestController
@RequestMapping("api/enseignants/")
public interface EnseignantEndpoint {

    @Operation(description = "Création d'une entité Enseignant")
    @ApiResponse(responseCode = "201", description = "L'entité Enseignant a bien été créée.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createEntityEnseignant(@RequestBody CreateEnseignantRequest request);

    @Operation(description = "Récupération d'une entité Enseignant par son email")
    @ApiResponse(responseCode = "200", description = "L'entité Enseignant a bien été récupérée.",
    content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_STREAM_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
           content = @Content(schema = @Schema(implementation = EnseignantNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{idEnseignant}")
    EnseignantDTO getEntityEnseignantById(@PathVariable("idEnseignant") Long idEnseignant) throws Exception;


    //get all enseignants
    @Operation(description = "Récupération de toutes les entités enseignant en bd")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités enseignant",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<EnseignantDTO> getAllEnseignants() throws Exception;



    @Operation(description = "Création d'une entité miahoot et ajout dans la liste des miahoot de l'enseignant")
    //@ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si le miahoot na pas pu etre creer",
      //      content = @Content(schema = @Schema(implementation = TestNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "201", description = "L'entité Miahoot a bien été créée et ajoutée à la liste de Miahoots de l'enseignant.")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{idEnseignant}/miahoots")
    public void createEntityMiahootFromEnseignant(@PathVariable("idEnseignant") Long idEnseignant, @RequestBody CreateMiahootRequest request) throws Exception;

    //delete enseignant by mail
    @Operation(description = "Suppression d'une entité enseignant en bd")
    @ApiResponse(responseCode = "200", description = "si isInError est à false alors l'element est renvoyé et supprimé")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{idEnseignant}")
    void deleteEnseignantEntity(@PathVariable("idEnseignant") Long idEnseignant) throws Exception;


    @Operation(description = "Modification d'une entité enseignant en bd")
    @ApiResponse(responseCode = "202", description = "l'entité a bien été modifiée")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être modifiée",
            content = @Content(schema = @Schema(implementation = TestNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @Error400Custom
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("{idEnseignant}")
    void updateEnseignantEntity(@PathVariable("idEnseignant") Long idEnseignant, @RequestBody EnseignantDTO enseignantDTO) throws Exception;


    //ajouter une question au mihaoot d'un enseignant


    @Operation(description = "Ajout d'une question à un miahoot d'un enseignant")
    @ApiResponse(responseCode = "202", description = "la question a bien été ajoutée")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{idEnseignant}/miahoots/{idMiahoot}/questions")
    void addQuestionToMiahoot(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot, @RequestBody CreateQuestionRequest createQuestionRequest) throws Exception;

    //get all questions of a miahoot of enseignant
    @Operation(description = "Récupération de toutes les questions d'un miahoot d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités question",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{idEnseignant}/miahoots/{idMiahoot}/questions")
    List<QuestionDTO> getAllQuestionsOfMiahootOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot) throws Exception;





    //get all miahoots of enseignant
    @Operation(description = "Récupération de tous les miahoots d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités miahoot",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{idEnseignant}/miahoots")
    List<MiahootDTO> getAllMiahootsOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant) throws Exception;

    //get miahoot of enseignant
    @Operation(description = "recupere le miahoot avec l'id correspondant dans la liste de miahoot de l'enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une entité miahoot avec l'id correspondant a  celui passé en parametre",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{idEnseignant}/miahoots/{idMiahoot}")
    MiahootDTO getMiahootOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot) throws Exception;


    //delete Miahoot of  enseignant by mail and idMiahoot
    @Operation(description = "Suppression d'une entité enseignant en bd")
    @ApiResponse(responseCode = "200", description = "si  l'element est renvoyé et supprimé")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{idEnseignant}/miahoots/{idMiahoot}")
    void deleteMiahootOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant,@PathVariable("idMiahoot") Long idMiahoot) throws  Exception;



    //creation d'une reponse dans un miahoot d'un enseignant
    //enseignant/{mail}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses post

    @Operation(description = "Ajout d'une reponse à une question d'un miahoot d'un enseignant")
    @ApiResponse(responseCode = "202", description = "la reponse a bien été ajoutée")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{idEnseignant}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses")
    void addReponseToQuestionOfMiahoot(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion, @RequestBody CreateReponseRequest createReponseRequest) throws Exception;


    //get all reponses of a question of a miahoot of enseignant
    @Operation(description = "Récupération de toutes les reponses d'une question d'un miahoot d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités reponse",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{idEnseignant}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses")
    List<ReponseDTO> getAllReponsesOfQuestionOfMiahootOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion) throws Exception;

    //get one reponse of a question of a miahoot of enseignant by idReponse
    @Operation(description = "recupere la reponse avec l'id correspondant dans la liste de reponse de la question du miahoot de l'enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une entité reponse avec l'id correspondant a  celui passé en parametre",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{idEnseignant}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses/{idReponse}")
    ReponseDTO getReponseOfQuestionOfMiahootOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion, @PathVariable("idReponse") Long idReponse) throws Exception;


    //delete on reponse of a question of a miahoot of enseignant by idReponse
    @Operation(description = "Suppression d'une entité enseignant en bd")
    @ApiResponse(responseCode = "200", description = "si  l'element est renvoyé et supprimé")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{idEnseignant}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses/{idReponse}")
    void deleteReponseOfQuestionOfMiahootOfEnseignant(@PathVariable("idEnseignant") Long idEnseignant, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion, @PathVariable("idReponse") Long idReponse) throws Exception;



    //create a miahoots and all his questions and all his reponses of enseignant by its id by taking a json in body
    @Operation(description = "Récupération d'un miahoot et de toutes ses questions et de toutes ses reponses d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Créer un miahoot entier",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("{mail}/miahoot")
    void createMiahootOfEnseignant(@PathVariable("mail") String mail, @RequestBody CreateFullMiahootRequest createFullMiahootRequest) throws Exception;
}