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
    public void createEntityMiahootFromEnseignant(Long idEnseignant, CreateMiahootRequest request) throws Exception {
        enseignantService.createMiahootFromEnseignant(idEnseignant,request);
    }

    @Override
    public void deleteEnseignantEntity(Long idEnseignant) throws EnseignantEntityNotFoundException {
        enseignantService.deleteEnseignantById(idEnseignant);
    }

    @Override
    public void updateEnseignantEntity(Long idEnseignant, EnseignantDTO enseignant) {
        enseignantService.updateEnseignant(idEnseignant, enseignant);
    }

    @Override
    public void addQuestionToMiahoot(Long idEnseignant, Long idMiahoot, CreateQuestionRequest createQuestionRequest) throws Exception {
        enseignantService.addQuestionToMiahoot(idEnseignant, idMiahoot , createQuestionRequest);
    }

    //get all question of a miahoot of an enseignant
    @Override
    public List<QuestionDTO> getAllQuestionsOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws Exception {
        return enseignantService.getAllQuestionsOfMiahootOfEnseignant(idEnseignant, idMiahoot);
    }

    @Override
    public List<MiahootDTO> getAllMiahootsOfEnseignant(Long idEnseignant) throws Exception {
        return enseignantService.getAllMiahootsOfEnseignant(idEnseignant);
    }

    @Override
    public MiahootDTO getMiahootOfEnseignant(final Long idEnseignant, final Long idMiahoot) throws Exception {
        return enseignantService.getMiahootOfEnseignant(idEnseignant,idMiahoot);
    }

    @Override
    public void deleteMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws Exception {
        enseignantService.deleteMiahootOfEnseignant(idEnseignant,idMiahoot);
    }

    @Override
    public void addReponseToQuestionOfMiahoot(Long idEnseignant, Long idMiahoot, Long idQuestion, CreateReponseRequest createReponseRequest) throws Exception {
        enseignantService.addReponseToQuestionOfMiahoot(idEnseignant,idMiahoot,idQuestion,createReponseRequest);
    }

    @Override
    public List<ReponseDTO> getAllReponsesOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion) throws Exception {
        return enseignantService.getAllReponsesOfQuestionOfMiahootOfEnseignant(idEnseignant,idMiahoot,idQuestion);
    }

    @Override
    public ReponseDTO getReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) throws Exception {
        return enseignantService.getReponseOfQuestionOfMiahootOfEnseignant(idEnseignant,idMiahoot,idQuestion,idReponse);
    }

    @Override
    public void deleteReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) throws Exception {
        enseignantService.deleteReponseOfQuestionOfMiahootOfEnseignant(idEnseignant,idMiahoot,idQuestion,idReponse);
    }

    @Override
    public void createMiahootOfEnseignant(String mail, CreateFullMiahootRequest createFullMiahootRequest) throws Exception {
        enseignantService.createMiahootOfEnseignant(mail, createFullMiahootRequest);
    }



}
