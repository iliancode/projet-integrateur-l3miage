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
    public void addReponseToQuestionOfMiahoot(Long idEnseignant, Long idMiahoot, Long idQuestion, CreateReponseRequest createReponseRequest) throws Exception {
        reponseService.addReponseToQuestionOfMiahoot(idEnseignant,idMiahoot,idQuestion,createReponseRequest);
    }

    @Override
    public List<ReponseDTO> getAllReponsesOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion) throws Exception {
        return reponseService.getAllReponsesOfQuestionOfMiahootOfEnseignant(idEnseignant,idMiahoot,idQuestion);
    }

    @Override
    public ReponseDTO getReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) {
        return reponseService.getReponseOfQuestionOfMiahootOfEnseignant(idEnseignant,idMiahoot,idQuestion,idReponse);
    }

    @Override
    public void deleteReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) {
        reponseService.deleteReponseOfQuestionOfMiahootOfEnseignant(idEnseignant,idMiahoot,idQuestion,idReponse);
    }
}
