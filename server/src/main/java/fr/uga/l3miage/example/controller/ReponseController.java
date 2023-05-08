package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.ReponseEndpoint;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponseDTO;
import fr.uga.l3miage.example.service.ReponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReponseController implements ReponseEndpoint {
    private final ReponseService reponseService;

    @Override
    public void addReponseToQuestionOfMiahoot(String uidEnseignant, Long idMiahoot, Long idQuestion, CreateReponseRequest createReponseRequest) throws Exception {
        reponseService.addReponseToQuestionOfMiahoot(uidEnseignant,idMiahoot,idQuestion,createReponseRequest);
    }

    @Override
    public List<ReponseDTO> getAllReponsesOfQuestionOfMiahootOfEnseignant(String uidEnseignant, Long idMiahoot, Long idQuestion) throws Exception {
        return reponseService.getAllReponsesOfQuestionOfMiahootOfEnseignant(uidEnseignant,idMiahoot,idQuestion);
    }

    @Override
    public ReponseDTO getReponseOfQuestionOfMiahootOfEnseignant(String uidEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) {
        return reponseService.getReponseOfQuestionOfMiahootOfEnseignant(uidEnseignant,idMiahoot,idQuestion,idReponse);
    }

    @Override
    public void deleteReponseOfQuestionOfMiahootOfEnseignant(String uidEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) {
        reponseService.deleteReponseOfQuestionOfMiahootOfEnseignant(uidEnseignant,idMiahoot,idQuestion,idReponse);
    }

/*
    @Override
    public void deleteAllReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion) {
        reponseService.deleteAllReponseOfQuestionOfMiahootOfEnseignant(idEnseignant,idMiahoot,idQuestion);
    }*/
}
