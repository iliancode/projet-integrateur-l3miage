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

}
