package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.EnseignantComponent;
import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.exception.rest.alreadyUseRestException.MailAlreadyUseRestException;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.EnseignantEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.TestEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.TestEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.alreadyExistException.MailAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.TestEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
import fr.uga.l3miage.example.response.MiahootDTO;
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

    private final MiahootComponent miahootComponent;
    private final MiahootMapper miahootMapper;

    /**
     * @param createEnseignantRequest la requête qui permet de créer une entité enseignant
     */
    public void createEnseignant( final CreateEnseignantRequest createEnseignantRequest) {
        try {
            Enseignant newEnseignant = enseignantMapper.toEntity(createEnseignantRequest);
            enseignantComponent.createEnseignant(newEnseignant);
        } catch (MailAlreadyExistException e) {
            throw new MailAlreadyUseRestException(String.format("L'email existe déjà. Raison : [%s]", e.getMessage()),createEnseignantRequest.getMail(),e);
        }
    }



    @Transactional
    public void deleteEnseignantById(long id) throws Exception {
        try {
            enseignantComponent.deleteEnseignantById(id);
        } catch ( Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public EnseignantDTO getEnseignantByMail(final String mail) throws Exception {
        try {
            Enseignant enseignant = enseignantComponent.getEnseignantByMail(mail);
            EnseignantDTO enseignantDTO = enseignantMapper.toDto(enseignant);
            return enseignantMapper.toDto(enseignantComponent.getEnseignantByMail(mail));
        } catch (EnseignantEntityNotFoundException e) {
            throw new EnseignantEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", e.getMessage()),mail,e);
        }
    }

    public List<EnseignantDTO> getAllEnseignants() throws Exception {
        return enseignantMapper.toDto(enseignantComponent.getAllEnseignants());
    }

    /**
     * @param mail de l'entité Participant à supprimer
     */
    @Transactional
    public void deleteEnseignantByMail(String mail)  {
        try {
            enseignantComponent.deleteEnseignantByMail(mail);
        } catch (EnseignantEntityNotFoundException ex) {
            throw new EnseignantEntityNotDeletedRestException(ex.getMessage());
        }
    }


    public void updateEnseignant (final String lastMail, final EnseignantDTO enseignant){
        try{
            enseignantComponent.updateEnseignantByMail(lastMail, enseignant);
        }catch (Exception ex){
            log.info("OUI OUI CEST BIEN ICI LE PROBLEME LAGUI");
            throw new TestEntityNotDeletedRestException(ex.getMessage());
        }
    }

    public void addQuestionToMiahoot(final String mail, final Long idMiahoot, final CreateQuestionRequest createQuestionRequest) throws Exception {
        Question newQuestion = questionMapper.toQuestion(createQuestionRequest);
        enseignantComponent.createQuestionInMiahoot(mail, idMiahoot, newQuestion);

    }

    @Transactional
    public void createMiahootFromEnseignant(final String mail, final CreateMiahootRequest createMiahootRequest) throws Exception {

        Miahoot newMiahoot = miahootMapper.toEntity(createMiahootRequest);
        enseignantComponent.createMiahootFromEnseignant(mail, newMiahoot);
    }

    public List<MiahootDTO> getAllMiahootsOfEnseignant(String mail) throws Exception {
        return enseignantMapper.toDtoMiahoot(enseignantComponent.getAllMiahootsOfEnseignant(mail));
    }

    // recupere le miahoot avec l'id correspondant dans la liste de miahoot de l'enseignant
    public MiahootDTO getMiahootOfEnseignant(final String mail, final Long idMiahoot) throws Exception {
            try {
                Miahoot miahoot = enseignantComponent.getMiahootOfEnseignant(mail, idMiahoot);
                return miahootMapper.toDto(miahoot);
            } catch (EnseignantEntityNotFoundException e) {
                throw new EnseignantEntityNotFoundRestException(String.format("Impossible de charger l'entité enseignant. Raison : [%s]", e.getMessage()), mail, e);
            } catch (TestEntityNotFoundException e) {
                throw new TestEntityNotFoundRestException(String.format("Impossible de charger l'entité Miahoot. Raison : [%s]", e.getMessage()), "erreur", e);
            }
    }
}
