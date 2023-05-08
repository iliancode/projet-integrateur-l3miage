package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.EnseignantEndpoint;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
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


    @Override
    public void createEntityEnseignant(CreateEnseignantRequest request) {
        enseignantService.createEnseignant(request);
    }

    @Override
    public EnseignantDTO getEntityEnseignantByUid(final String uid) throws Exception {
        return enseignantService.getEnseignantByUid(uid);
    }

    @Override
    public List<EnseignantDTO> getAllEnseignants() {
        return enseignantService.getAllEnseignants();
    }

    @Override
    public void deleteEnseignantEntity(Long idEnseignant) {
        enseignantService.deleteEnseignantById(idEnseignant);
    }

    @Override
    public void updateEnseignantEntity(Long idEnseignant, CreateEnseignantRequest request) {
        enseignantService.updateEnseignant(idEnseignant, request);
    }

}
