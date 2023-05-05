package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.EnseignantEndpoint;
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
    public EnseignantDTO getEntityEnseignantById(final Long idEnseignant) throws Exception {
        return enseignantService.getEnseignantById(idEnseignant);
    }

    @Override
    public List<EnseignantDTO> getAllEnseignants() throws Exception {
        return enseignantService.getAllEnseignants();
    }
    
    @Override
    public void deleteEnseignantEntity(Long idEnseignant) throws EnseignantEntityNotFoundException {
        enseignantService.deleteEnseignantById(idEnseignant);
    }

    @Override
    public void updateEnseignantEntity(Long idEnseignant, EnseignantDTO enseignant) {
        enseignantService.updateEnseignant(idEnseignant, enseignant);
    }

}
