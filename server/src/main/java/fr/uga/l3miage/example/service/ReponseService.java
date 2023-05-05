package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.ReponseComponent;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.ReponseEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.ReponseEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.ReponseEntityNotFoundException;
import fr.uga.l3miage.example.mapper.ReponseMapper;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.request.CreateReponseRequest;
import fr.uga.l3miage.example.response.ReponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReponseService {

    private final ReponseComponent reponseComponent;
    private final ReponseMapper reponseMapper;


    public void addReponseToQuestionOfMiahoot(final Long idEnseignant, final Long idMiahoot, final Long idQuestion, final CreateReponseRequest createReponseRequest) throws Exception {
        Reponse newReponse = reponseMapper.toEntity(createReponseRequest);
        reponseComponent.addReponseToQuestionOfMiahoot(idEnseignant, idMiahoot, idQuestion, newReponse);
    }


    public List<ReponseDTO> getAllReponsesOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion) throws Exception {
        return reponseMapper.toDto(reponseComponent.getAllReponsesOfQuestionOfMiahootOfEnseignant(idEnseignant, idMiahoot, idQuestion));
    }


    public ReponseDTO getReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) throws Exception {
        return reponseMapper.toDto(reponseComponent.getReponseOfQuestionOfMiahootOfEnseignant(idEnseignant, idMiahoot, idQuestion, idReponse));
    }


    public void deleteReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) throws Exception {
        try{
            reponseComponent.deleteReponseOfQuestionOfMiahootOfEnseignant(idEnseignant, idMiahoot, idQuestion, idReponse);
        }catch (Exception ex){
            throw new Exception("Erreur lors de la suppression de la reponse");
        }
    }


    public void updateEntityReponse(final Long idEnseignant, final Long idMiahoot, final Long )
}
