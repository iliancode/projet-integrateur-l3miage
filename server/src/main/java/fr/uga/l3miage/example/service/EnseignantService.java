package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.EnseignantComponent;
import fr.uga.l3miage.example.exception.rest.alreadyUseRestException.MailAlreadyUseRestException;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.EnseignantEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.TestEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.MiahootEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.TestEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.alreadyExistException.MailAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.*;
import fr.uga.l3miage.example.models.*;
import fr.uga.l3miage.example.request.*;
import fr.uga.l3miage.example.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnseignantService {

    private final EnseignantComponent enseignantComponent;
    private final EnseignantMapper enseignantMapper;
    private final QuestionMapper questionMapper;

    private final MiahootMapper miahootMapper;
    private final ReponseMapper reponseMapper;

    private final PartieMapper partieMapper;

    /**
     * @param createEnseignantRequest la requête qui permet de créer une entité enseignant
     */
    public void createEnseignant(final CreateEnseignantRequest createEnseignantRequest) {
        try {
            Enseignant newEnseignant = enseignantMapper.toEntity(createEnseignantRequest);
            enseignantComponent.createEnseignant(newEnseignant);
        } catch (MailAlreadyExistException e) {
            throw new MailAlreadyUseRestException(String.format("L'email existe déjà. Raison : [%s]", e.getMessage()), createEnseignantRequest.getMail(), e);
        }
    }

    public EnseignantDTO getEnseignantById(final Long idEnseignant) throws Exception {
        try {
            return enseignantMapper.toDto(enseignantComponent.getEnseignantById(idEnseignant));
        } catch (EnseignantEntityNotFoundException e) {
            throw new EnseignantEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", e.getMessage()),idEnseignant,e);
        }
    }

    public List<EnseignantDTO> getAllEnseignants() {
        return enseignantMapper.toDto(enseignantComponent.getAllEnseignants());
    }

    /**
     * @param idEnseignant de l'entité Participant à supprimer
     */
    @Transactional
    public void deleteEnseignantById(Long idEnseignant) throws EnseignantEntityNotFoundException {
        try {
            enseignantComponent.deleteEnseignantById(idEnseignant);
        } catch (EnseignantEntityNotDeletedRestException ex) {
            throw new EnseignantEntityNotDeletedRestException(ex.getMessage());
        }
    }


    public void updateEnseignant (final Long idEnseignant, final EnseignantDTO enseignant) {
        try{
            enseignantComponent.updateEnseignantById(idEnseignant, enseignant);
        }catch (Exception ex) {
            throw new TestEntityNotDeletedRestException(ex.getMessage());
        }
    }

    public void addQuestionToMiahoot(final Long idEnseignant, final Long idMiahoot, final CreateQuestionRequest createQuestionRequest) throws Exception {
        Question newQuestion = questionMapper.toQuestion(createQuestionRequest);
        enseignantComponent.createQuestionInMiahoot(idEnseignant, idMiahoot, newQuestion);

    }

    @Transactional
    public void createMiahootFromEnseignant(final Long idEnseignant, final CreateMiahootRequest createMiahootRequest) throws Exception {

        Miahoot newMiahoot = miahootMapper.toEntity(createMiahootRequest);
        enseignantComponent.createMiahootFromEnseignant(idEnseignant, newMiahoot);
    }

    public List<MiahootDTO> getAllMiahootsOfEnseignant(Long idEnseignant) throws Exception {
        return enseignantMapper.toDtoMiahoot(enseignantComponent.getAllMiahootsOfEnseignant(idEnseignant));
    }

    public List<QuestionDTO> getAllQuestionsOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws Exception {
        return enseignantMapper.toDtoQuestion(enseignantComponent.getAllQuestionsOfMiahootOfEnseignant(idEnseignant, idMiahoot));
    }

    // recupere le miahoot avec l'id correspondant dans la liste de miahoot de l'enseignant
    public MiahootDTO getMiahootOfEnseignant(final Long idEnseignant, final Long idMiahoot) throws Exception {
            try {
                Miahoot miahoot = enseignantComponent.getMiahootOfEnseignant(idEnseignant, idMiahoot);
                return miahootMapper.toDto(miahoot);
            } catch (EnseignantEntityNotFoundException e) {
                throw new EnseignantEntityNotFoundRestException(String.format("Impossible de charger l'entité enseignant. Raison : [%s]", e.getMessage()), idEnseignant, e);
            } catch (TestEntityNotFoundRestException e) {
                throw new TestEntityNotFoundRestException(String.format("Impossible de charger l'entité Miahoot. Raison : [%s]", e.getMessage()), "erreur", e);
            }
    }

    public void deleteMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws  Exception {
        try {
            enseignantComponent.deleteMiahootOfEnseignant(idEnseignant, idMiahoot);
        } catch (EnseignantEntityNotFoundException e) {
            throw new EnseignantEntityNotFoundRestException(String.format("Impossible de charger l'entité enseignant. Raison : [%s]", e.getMessage()), idEnseignant, e);
        } catch (TestEntityNotFoundRestException e) {
            throw new TestEntityNotFoundRestException(String.format("Impossible de charger l'entité Miahoot. Raison : [%s]", e.getMessage()), "erreur", e);
        }
    }

    public void addReponseToQuestionOfMiahoot(final Long idEnseignant, final Long idMiahoot, final Long idQuestion, final CreateReponseRequest createReponseRequest) throws Exception {
        Reponse newReponse = reponseMapper.toReponse(createReponseRequest);
        enseignantComponent.addReponseToQuestionOfMiahoot(idEnseignant, idMiahoot, idQuestion, newReponse);
    }

    public List<ReponseDTO> getAllReponsesOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion) throws Exception {
        return enseignantMapper.toDtoReponse(enseignantComponent.getAllReponsesOfQuestionOfMiahootOfEnseignant(idEnseignant, idMiahoot, idQuestion));
    }

    public ReponseDTO getReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) throws Exception {
        return reponseMapper.toDto(enseignantComponent.getReponseOfQuestionOfMiahootOfEnseignant(idEnseignant, idMiahoot, idQuestion, idReponse));
    }

    public void deleteReponseOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion, Long idReponse) throws Exception {
        try{
            enseignantComponent.deleteReponseOfQuestionOfMiahootOfEnseignant(idEnseignant, idMiahoot, idQuestion, idReponse);
        }catch (Exception ex){
            throw new Exception("Erreur lors de la suppression de la reponse");
        }
    }
}
