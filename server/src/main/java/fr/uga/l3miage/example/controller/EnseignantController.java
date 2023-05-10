package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.EnseignantEndpoint;
import fr.uga.l3miage.example.request.*;
import fr.uga.l3miage.example.response.*;
import fr.uga.l3miage.example.service.EnseignantService;
import fr.uga.l3miage.example.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EnseignantController implements EnseignantEndpoint {

    public final EnseignantService enseignantService;
    public final ParticipantService participantService;


    /**
     * Controller pour créer un enseignant
     * @param request : requête de création d'un enseignant
     */
    @Override
    public void createEntityEnseignant(CreateEnseignantRequest request) {
        enseignantService.createEnseignant(request);
    }


    /**
     * Controller pour récupérer un enseignant par son uid
     * @param uid : uid de l'enseignant
     * @return l'EnseignantDTO
     */
    @Override
    public EnseignantDTO getEntityEnseignantByUid(final String uid) {
        return enseignantService.getEnseignantByUid(uid);
    }


    /**
     * Controller pour récupérer la liste de tous les enseignants
     * @return la liste d'EnseignantDTO
     */
    @Override
    public List<EnseignantDTO> getAllEnseignants() {
        return enseignantService.getAllEnseignants();
    }


    /**
     * Controller pour effacer un enseignant par son uid
     * @param uidEnseignant : uid de l'enseignant
     */
    @Override
    public void deleteEnseignantEntity(String uidEnseignant) {
        enseignantService.deleteEnseignantByUid(uidEnseignant);
    }


    // OPTIONNEL
    @Override
    public void updateEnseignantEntity(String uidEnseignant, CreateEnseignantRequest request) {
        enseignantService.updateEnseignant(uidEnseignant, request);
    }

}
