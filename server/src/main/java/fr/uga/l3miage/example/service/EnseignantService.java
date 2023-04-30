package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.EnseignantComponent;
import fr.uga.l3miage.example.exception.rest.TestEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.MultipleEntityHaveSameDescriptionException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.TestEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
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

    /**
     * @param createEnseignantRequest la requête qui permet de créer une entité enseignant
     */
    public void createEnseignant( final CreateEnseignantRequest createEnseignantRequest) {
        Enseignant newEnseignant = enseignantMapper.toEntity(createEnseignantRequest);
        enseignantComponent.createEnseignant(newEnseignant);
    }



    @Transactional
    public void deleteEnseignantById(long id) throws Exception {
        try {
            enseignantComponent.deleteEnseignantById(id);
        } catch ( Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * @param mail de l'entité enseignant recherché
     * @return le dto enseignant correspondant au mail
     */
    public EnseignantDTO getEnseignantByMail(final String mail) {
        try {
            return enseignantMapper.toDto(enseignantComponent.getEnseignantByMail(mail));
        } catch (EnseignantEntityNotFoundException e) {
            throw new EnseignantEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]", e.getMessage()),mail,e);
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
}
