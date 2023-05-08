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
    public void addPartieToEnseignant(String uidEnseignant, Long idMiahoot, CreatePartieRequest createPartieRequest) {
        partieService.addPartieToEnseignant(uidEnseignant,idMiahoot,createPartieRequest);
    }


    @Override
    public PartieDTO getPartieFromEnseignant(String uidEnseignant, Long codePartie) throws Exception {
        return partieService.getPartieFromEnseignant(uidEnseignant,codePartie);
    }


    @Override
    public void deletePartieFromEnseignant(String uidEnseignant, Long codePartie) throws Exception {
        partieService.deletePartieFromEnseignant(uidEnseignant,codePartie);
    }
}
