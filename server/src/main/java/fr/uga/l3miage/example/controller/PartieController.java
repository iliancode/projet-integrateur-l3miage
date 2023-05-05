package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.PartieEndpoint;
import fr.uga.l3miage.example.request.CreatePartieRequest;
import fr.uga.l3miage.example.response.PartieDTO;
import fr.uga.l3miage.example.service.PartieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PartieController implements PartieEndpoint {
    private final PartieService partieService;


    @Override
    public void addPartieToEnseignant(Long idEnseignant, Long idMiahoot, CreatePartieRequest createPartieRequest) {
        partieService.addPartieToEnseignant(idEnseignant,idMiahoot,createPartieRequest);
    }


    @Override
    public PartieDTO getPartieFromEnseignant(Long idEnseignant, Long codePartie) throws Exception {
        return partieService.getPartieFromEnseignant(idEnseignant,codePartie);
    }


    @Override
    public void deletePartieFromEnseignant(Long idEnseignant, Long codePartie) throws Exception {
        partieService.deletePartieFromEnseignant(idEnseignant,codePartie);
    }
}
