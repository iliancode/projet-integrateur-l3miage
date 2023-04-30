package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.EnseignantComponent;
import fr.uga.l3miage.example.exception.rest.alreadyUseRestException.MailAlreadyUseRestException;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.EnseignantEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.alreadyExistException.MailAlreadyExistException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.EnseignantMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.request.CreateEnseignantRequest;
import fr.uga.l3miage.example.response.EnseignantDTO;
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

    public List<EnseignantDTO> getAllEnseignants() {
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


    public void updateEnseignant (final String lastMail, final CreateEnseignantRequest request) {
        try{
            enseignantComponent.updateEnseignantByMail(lastMail, request);
        }catch (EnseignantEntityNotFoundException ex) {
            throw new EnseignantEntityNotDeletedRestException(ex.getMessage());
        } catch (MailAlreadyExistException ex) {
            throw new MailAlreadyUseRestException(ERROR_DETECTED,request.getMail(),ex);
        }
    }
}
