package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.error.notFoundErrorResponse.EnseignantNotFoundErrorResponse;
import fr.uga.l3miage.example.error.notFoundErrorResponse.TestNotFoundErrorResponse;
import fr.uga.l3miage.example.request.*;
import fr.uga.l3miage.example.response.EnseignantDTO;
import fr.uga.l3miage.example.response.MiahootDTO;
import fr.uga.l3miage.example.response.QuestionDTO;
import fr.uga.l3miage.example.response.ReponseDTO;
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
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'est pas trouvée",
           content = @Content(schema = @Schema(implementation = EnseignantNotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{mail}")
    EnseignantDTO getEntityEnseignantByMail(@PathVariable("mail") String mail) throws Exception;


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
    @PostMapping("{mail}/miahoots")
    public void createEntityMiahootFromEnseignant(@PathVariable String mail, @RequestBody CreateMiahootRequest request) throws Exception;

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


    //ajouter une question au mihaoot d'un enseignant


    @Operation(description = "Ajout d'une question à un miahoot d'un enseignant")
    @ApiResponse(responseCode = "202", description = "la question a bien été ajoutée")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{mail}/miahoots/{idMiahoot}/questions")
    void addQuestionToMiahoot(@PathVariable("mail") String mail, @PathVariable("idMiahoot") Long idMiahoot, @RequestBody CreateQuestionRequest createQuestionRequest) throws Exception;

    //get all questions of a miahoot of enseignant
    @Operation(description = "Récupération de toutes les questions d'un miahoot d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités question",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{mail}/miahoots/{idMiahoot}/questions")
    List<QuestionDTO> getAllQuestionsOfMiahootOfEnseignant(@PathVariable("mail") String mail, @PathVariable("idMiahoot") Long idMiahoot) throws Exception;





    //get all miahoots of enseignant
    @Operation(description = "Récupération de tous les miahoots d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités miahoot",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{mail}/miahoots")
    List<MiahootDTO> getAllMiahootsOfEnseignant(@PathVariable("mail") String mail) throws Exception;

    //get miahoot of enseignant
    @Operation(description = "recupere le miahoot avec l'id correspondant dans la liste de miahoot de l'enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une entité miahoot avec l'id correspondant a  celui passé en parametre",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{mail}/miahoots/{idMiahoot}")
    MiahootDTO getMiahootOfEnseignant(@PathVariable("mail") String mail,@PathVariable("idMiahoot") Long idMiahoot) throws Exception;


    //delete Miahoot of  enseignant by mail and idMiahoot
    @Operation(description = "Suppression d'une entité enseignant en bd")
    @ApiResponse(responseCode = "200", description = "si  l'element est renvoyé et supprimé")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{mail}/miahoots/{idMiahoot}")
    void deleteMiahootOfEnseignant(@PathVariable("mail") String mail,@PathVariable("idMiahoot") Long idMiahoot) throws  Exception;



    //creation d'une reponse dans un miahoot d'un enseignant
    //enseignant/{mail}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses post

    @Operation(description = "Ajout d'une reponse à une question d'un miahoot d'un enseignant")
    @ApiResponse(responseCode = "202", description = "la reponse a bien été ajoutée")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{mail}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses")
    void addReponseToQuestionOfMiahoot(@PathVariable("mail") String mail, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion, @RequestBody CreateReponseRequest createReponseRequest) throws Exception;


    //get all reponses of a question of a miahoot of enseignant
    @Operation(description = "Récupération de toutes les reponses d'une question d'un miahoot d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités reponse",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{mail}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses")
    List<ReponseDTO> getAllReponsesOfQuestionOfMiahootOfEnseignant(@PathVariable("mail") String mail, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion) throws Exception;

    //get one reponse of a question of a miahoot of enseignant by idReponse
    @Operation(description = "recupere la reponse avec l'id correspondant dans la liste de reponse de la question du miahoot de l'enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une entité reponse avec l'id correspondant a  celui passé en parametre",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{mail}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses/{idReponse}")
    ReponseDTO getReponseOfQuestionOfMiahootOfEnseignant(@PathVariable("mail") String mail, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion, @PathVariable("idReponse") Long idReponse) throws Exception;


    //delete on reponse of a question of a miahoot of enseignant by idReponse
    @Operation(description = "Suppression d'une entité enseignant en bd")
    @ApiResponse(responseCode = "200", description = "si  l'element est renvoyé et supprimé")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{mail}/miahoots/{idMiahoot}/questions/{idQuestion}/reponses/{idReponse}")
    void deleteReponseOfQuestionOfMiahootOfEnseignant(@PathVariable("mail") String mail, @PathVariable("idMiahoot") Long idMiahoot, @PathVariable("idQuestion") Long idQuestion, @PathVariable("idReponse") Long idReponse) throws Exception;


    //post une partie dans un enseignant et choisir un miahoot a injecter dans la partie par son id
    @Operation(description = "Ajout d'une partie à un enseignant")
    @ApiResponse(responseCode = "202", description = "la partie a bien été ajoutée")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{mail}/parties/{idMiahoot}")
    void addPartieToEnseignant(@PathVariable("mail") String mail,@PathVariable("idMiahoot") Long idMiahoot, @RequestBody CreatePartieRequest createPartieRequest) throws Exception;
}