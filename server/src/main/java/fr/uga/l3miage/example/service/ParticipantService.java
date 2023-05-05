package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.ParticipantComponent;
import fr.uga.l3miage.example.component.PartieComponent;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.ParticipantEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.ParticipantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.PartieEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.ParticipantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.PartieEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.isNotEntityOf.IsNotPartieOfEnseignantException;
import fr.uga.l3miage.example.mapper.ParticipantMapper;
import fr.uga.l3miage.example.models.Participant;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.request
        .CreateParticipantRequest;
import fr.uga.l3miage.example.response.ParticipantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantComponent participantComponent;
    private final ParticipantMapper participantMapper;
    private final PartieComponent partieComponent;


    /**
     * @param codePartie le code de la partie
     * @param createParticipantRequest la requête qui permet de créer une entité participant dans une Partie
     */
    public void createParticipantByPartie(final Long codePartie, final CreateParticipantRequest createParticipantRequest) {
        try {
            Partie partie = partieComponent.getPartie(codePartie);
            Participant participant = participantMapper.toEntity(createParticipantRequest);
            participantComponent.createParticipantByPartie(partie, participant);
        } catch (PartieEntityNotFoundException e) {
            throw new PartieEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]",e.getMessage()),codePartie,e);
        }
    }


    /**
     * @param codePartie le code de la partie
     * @return la liste des dto participant d'une partie
     */
    public List<ParticipantDTO> getAllParticipantsByPartie(final Long codePartie) {
        try {
            Partie partie = partieComponent.getPartie(codePartie);
            return participantMapper.toListDto(participantComponent.getAllParticipantsOfPartie(partie));
        } catch (PartieEntityNotFoundException e) {
            throw new ParticipantEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]",e.getMessage()),codePartie,e);
        }
    }


    /**
     * @param codePartie le code de la partie
     * @param id l'id du participant
     * @return le dto participant d'une partie
     */
    public ParticipantDTO getParticipantByPartie(final Long codePartie, final Long id) {
        try {
            Partie partie = partieComponent.getPartie(codePartie);
            return participantMapper.toDto(participantComponent.getParticipantOfPartie(partie, id));
        } catch (PartieEntityNotFoundException e) {
            throw new PartieEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]",e.getMessage()),id,e);
        } catch (ParticipantEntityNotFoundException e) {
            throw new ParticipantEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]",e.getMessage()),id,e);
        }
    }


    /**
     * @param idEnseignant l'id de l'enseignant
     * @param codePartie le code de la partie
     */
    public void deleteAllParticipantsFromPartie(final Long idEnseignant, final Long codePartie) {
        try {
            Partie partie = partieComponent.getPartie(codePartie);
            participantComponent.deleteAllParticipantsFromPartie(idEnseignant, partie);
        } catch (EnseignantEntityNotFoundException | PartieEntityNotFoundException | IsNotPartieOfEnseignantException e) {
            throw new ParticipantEntityNotDeletedRestException(e.getMessage());
        }
    }

}