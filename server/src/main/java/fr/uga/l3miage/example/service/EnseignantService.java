package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.EnseignantComponent;
import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.exception.rest.TestEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.technical.MultipleEntityHaveSameDescriptionException;
import fr.uga.l3miage.example.exception.technical.TestEntityNotFoundException;
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

    public void createEnseignant( final CreateEnseignantRequest createEnseignantRequest) throws Exception {
        Enseignant newEnseignant = enseignantMapper.toEntity(createEnseignantRequest);

       /* if(newEnseignant.getMail().length() !=0){
            if(newEnseignant.getPseudo().length() != 0){
                if(newEnseignant.getMdp().length() >9){
                    try{*/
                        enseignantComponent.createEnseignant(newEnseignant);
                    /*} catch (Exception e) {
                        throw new Exception("erreur a la creation de l'enseignant");
                    }
                }else throw new Exception("mdp size <=9 ");
            }else throw new Exception("pseudo size = 0 ");
        }else throw new Exception("mail size = 0 ");*/
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
        } catch (Exception ex) {
            throw new Exception("Impossible de charger l'entité. Raison :" +ex.getMessage());
        }
    }

    public List<EnseignantDTO> getAllEnseignants() throws Exception {
        return enseignantMapper.toDto(enseignantComponent.getAllEnseignants());
    }


    @Transactional
    public void deleteEnseignantByMail(String mail)  {
        try {
            enseignantComponent.deleteEnseignantByMail(mail);
        } catch (MultipleEntityHaveSameDescriptionException | TestEntityNotFoundException ex) {
            throw new TestEntityNotDeletedRestException(ex.getMessage());
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
}
