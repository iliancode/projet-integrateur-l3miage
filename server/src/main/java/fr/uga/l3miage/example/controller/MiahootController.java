package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.MiahootEndpoint;
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
    public void createEntityMiahootFromEnseignant(Long idEnseignant, CreateMiahootRequest request) throws Exception {
        miahootService.createMiahootFromEnseignant(idEnseignant,request);
    }

    @Override
    public List<MiahootDTO> getAllMiahootsOfEnseignant(Long idEnseignant) throws Exception {
        return miahootService.getAllMiahootsOfEnseignant(idEnseignant);
    }

    @Override
    public MiahootDTO getMiahootOfEnseignant(final Long idEnseignant, final Long idMiahoot) throws Exception {
        return miahootService.getMiahootOfEnseignant(idEnseignant,idMiahoot);
    }

    @Override
    public void deleteMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws Exception {
        miahootService.deleteMiahootOfEnseignant(idEnseignant,idMiahoot);
    }
}
