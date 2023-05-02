package fr.uga.l3miage.example.endpoint;


import fr.uga.l3miage.example.annotations.Error400Custom;
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

}