package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.MiahootEndpoint;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;
import fr.uga.l3miage.example.response.QuestionDTO;
import fr.uga.l3miage.example.service.MiahootService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MiahootController implements MiahootEndpoint {

    public final MiahootService miahootService;


    @Override
    public MiahootDTO getEntityMiahoot(final Long id) throws Exception {
        return miahootService.getMiahoot(id);
    }

    @Override
    public void createEntityMiahoot(CreateMiahootRequest request) {
        miahootService.createMiahoot(request);
    }

    @Override
    public void deleteMiahootEntity(final Long id) throws Exception {
         miahootService.deleteParticipant(id);
    }

    @Override
    public QuestionDTO getQuestion(Long idMiahoot, Long idQuestion) throws Exception {
        log.info("entre dans controller getQuestion");
        return miahootService.getQuestion(idMiahoot, idQuestion);
    }

    @Override
    public void deleteQuestion(Long idMiahoot, Long idQuestion) throws Exception {
        miahootService.deleteQuestion(idMiahoot, idQuestion);
    }
}
