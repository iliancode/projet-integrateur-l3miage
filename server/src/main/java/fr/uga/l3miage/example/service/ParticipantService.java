package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.ParticipantComponent;
import fr.uga.l3miage.example.component.PartieComponent;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.ParticipantEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.ParticipantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.ParticipantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.PartieEntityNotFoundException;
import fr.uga.l3miage.example.mapper.ParticipantMapper;
import fr.uga.l3miage.example.models.Participant;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.request.CreateParticipantRequest;
import fr.uga.l3miage.example.response.ParticipantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantComponent participantComponent;
    private final ParticipantMapper participantMapper;
    private final PartieComponent partieComponent;


    /**
     * @param createParticipantRequest la requête qui permet de créer une entité participant dans une Partie
     */
    public void createParticipantByPartie(final Long codePartie, final CreateParticipantRequest createParticipantRequest) {
        try {
            Partie partie = partieComponent.getPartie(codePartie);
            Participant participant = participantMapper.toEntity(createParticipantRequest);
            participantComponent.createParticipantByPartie(partie, participant);
        } catch (PartieEntityNotFoundException e) {
            throw new ParticipantEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]",e.getMessage()),codePartie,e);
        }
    }

}