package fr.uga.l3miage.example.endpoint;

import fr.uga.l3miage.example.annotations.Error400Custom;
import fr.uga.l3miage.example.request.CreatePartieRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
import fr.uga.l3miage.example.response.PartieDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Endpoints des Parties")
@CrossOrigin
@RestController
@RequestMapping("api/")
public interface PartieEndpoint {

    @Operation(description = "Ajout d'une partie à un enseignant")
    @ApiResponse(responseCode = "202", description = "la partie a bien été ajoutée")
    @Error400Custom
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("enseignants/{uidEnseignant}/miahoots/{idMiahoot}/parties")
    void addPartieToEnseignant(@PathVariable("uidEnseignant") String uidEnseignant, @PathVariable("idMiahoot") Long idMiahoot, @RequestBody CreatePartieRequest createPartieRequest);


    @Operation(description = "Récupération une partie d'un enseignant")
    @ApiResponse(responseCode = "200", description = "Renvoie une liste d'entités partie",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("enseignants/{uidEnseignant}/parties/{codePartie}")
    PartieDTO getPartieFromEnseignant(@PathVariable("uidEnseignant") String uidEnseignant, @PathVariable("codePartie") Long codePartie) throws Exception;


    @Operation(description = "Suppression d'une partie d'un enseignant")
    @ApiResponse(responseCode = "200", description = "si  l'element est renvoyé et supprimé")
    @ApiResponse(responseCode = "404", description = "Renvoie une erreur 404 si l'entité n'a pu être supprimée",
            content = @Content(schema = @Schema(implementation = EnseignantDTO.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("enseignants/{uidEnseignant}/parties/{codePartie}")
    void deletePartieFromEnseignant(@PathVariable("uidEnseignant") String uidEnseignant, @PathVariable("codePartie") Long codePartie) throws Exception;
}
