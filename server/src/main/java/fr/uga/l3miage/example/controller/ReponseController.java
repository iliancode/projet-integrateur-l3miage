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
    public ReponseDTO getReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) throws Exception {
        return reponseService.getReponseOfQuestionOfMiahootOfEnseignant(idEnseignant,idMiahoot,idQuestion,idReponse);
    }

    @Override
    public void deleteReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) throws Exception {
        reponseService.deleteReponseOfQuestionOfMiahootOfEnseignant(idEnseignant,idMiahoot,idQuestion,idReponse);
    }

    /**
     * Modifie une reponse
     * @param idEnseignant l'id de l'enseignant
     * @param idMiahoot l'id du miahoot
     * @param idQuestion l'id de la question
     * @param idReponse l'id de la reponse
     * @param request la requete pour modifier la reponse
     */
    @Override
    public void updateReponseEntity(final Long idEnseignant, final Long idMiahoot, final Long idQuestion, final Long idReponse, final CreateReponseRequest request) {
        reponseService.updateReponseEntity(idEnseignant,idMiahoot,idQuestion,idReponse,request);
    }
}
