package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.MiahootEndpoint;
import fr.uga.l3miage.example.request.CreateFullMiahootRequest;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import fr.uga.l3miage.example.service.MiahootService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MiahootController implements MiahootEndpoint {
    private final MiahootService miahootService;

    @Override
    public void createEntityMiahootFromEnseignant(String uidEnseignant, CreateMiahootRequest request) throws Exception {
        miahootService.createMiahootFromEnseignant(uidEnseignant,request);
    }

    @Override
    public List<MiahootDTO> getAllMiahootsOfEnseignant(String uidEnseignant) throws Exception {
        return miahootService.getAllMiahootsOfEnseignant(uidEnseignant);
    }

    @Override
    public MiahootDTO getMiahootOfEnseignant(final String uidEnseignant, final Long idMiahoot) throws Exception {
        return miahootService.getMiahootOfEnseignant(uidEnseignant,idMiahoot);
    }

    @Override
    public void deleteMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws Exception {
        miahootService.deleteMiahootOfEnseignant(idEnseignant,idMiahoot);
    }

    @Override
    public void createMiahootOfEnseignant(String uidEnseignant, CreateFullMiahootRequest createFullMiahootRequest) throws Exception {
        miahootService.createMiahootOfEnseignant(uidEnseignant, createFullMiahootRequest);

    }
}
