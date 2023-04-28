package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.EnseignantEndpoint;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
import fr.uga.l3miage.example.response.MiahootDTO;
import fr.uga.l3miage.example.service.EnseignantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EnseignantController implements EnseignantEndpoint {

    public final EnseignantService enseignantService;
    public final EnseignantMapper enseignantMapper;
    @Override
    public void createEntityEnseignant(CreateEnseignantRequest request) throws Exception {
        enseignantService.createEnseignant(request);
    }

    @Override
    public EnseignantDTO getEntityEnseignantByMail(String mail) throws Exception {
        return enseignantService.getEnseignantByMail(mail);
    }

    @Override
    public void createEntityMiahootFromEnseignant(String mail, MiahootDTO miahootDTO) throws Exception {
        enseignantService.createMiahootFromEnseignant(mail,miahootDTO);
    }

    @Override
    public void deleteEnseignantEntity(String mail)  {
        enseignantService.deleteEnseignantByMail(mail);
    }

    @Override
    public void updateEnseignantEntity(String mail, EnseignantDTO enseignantDTO) throws Exception {
        enseignantService.updateEnseignant(mail, enseignantDTO);
    }


}
