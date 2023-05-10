package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.EnseignantComponent;
import fr.uga.l3miage.example.exception.rest.alreadyUseRestException.MailAlreadyUseRestException;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.EnseignantEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
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

import static fr.uga.l3miage.example.service.ExampleService.ERROR_DETECTED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnseignantService {

    private final EnseignantComponent enseignantComponent;
    private final EnseignantMapper enseignantMapper;


    /**
     * Service pour créer une entité enseignant
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


    /**
     * Service pour récupèrer un enseignant par son uid
     * @param uid de l'entité Enseignant à récupérer
     * @return l'EnseignantDTO correspondant à l'uid
     */
    public EnseignantDTO getEnseignantByUid(final String uid) {
        try {
            return enseignantMapper.toDto(enseignantComponent.getEnseignantByUid(uid));
        } catch (EnseignantEntityNotFoundException e) {
            throw new EnseignantEntityNotFoundRestException(e.getMessage(), uid, e);
        }
    }


    /**
     * Service pour récupèrer tous les enseignants
     * @return la liste de tous les EnseignantDTO
     */
    public List<EnseignantDTO> getAllEnseignants() {
        return enseignantMapper.toDto(enseignantComponent.getAllEnseignants());
    }


    /**
     * Service pour effacer un enseignant par son uid
     * @param uidEnseignant de l'entité Participant à supprimer
     */
    @Transactional
    public void deleteEnseignantByUid(final String uidEnseignant) {
        try {
            enseignantComponent.deleteEnseignantByUid(uidEnseignant);
        } catch (EnseignantEntityNotFoundException ex) {
            throw new EnseignantEntityNotDeletedRestException(ex.getMessage());
        }
    }


    // OPTIONNEL
    public void updateEnseignant (final String uidEnseignant, final CreateEnseignantRequest request) {
        try{
            enseignantComponent.updateEnseignantByUid(uidEnseignant, request);
        }catch (EnseignantEntityNotFoundException ex) {
            throw new EnseignantEntityNotDeletedRestException(ex.getMessage());
        } catch (MailAlreadyExistException ex) {
            throw new MailAlreadyUseRestException(ERROR_DETECTED,request.getMail(),ex);
        }
    }

}
