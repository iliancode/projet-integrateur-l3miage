package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.ParticipantComponent;
import fr.uga.l3miage.example.exception.rest.ParticipantEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.ParticipantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.ParticipantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.ParticipantMapper;
import fr.uga.l3miage.example.models.Participant;
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

    /**
     * @param id de l'entité participant recherchée
     * @return le dto participant correspondant à l'id
     */
    public ParticipantDTO getParticipant(final Long id) {
        try {
            return participantMapper.toDto(participantComponent.getParticipant(id));
        } catch (ParticipantEntityNotFoundException e) {
            throw new ParticipantEntityNotFoundRestException(String.format("Impossible de charger l'entité. Raison : [%s]",e.getMessage()),id,e);
        }
    }

    /**
     * @param createParticipantRequest la requête qui permet de créer une entité participant
     */
    public void createParticipant(final CreateParticipantRequest createParticipantRequest) {
        System.out.println("WSHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        Participant newParticipant = participantMapper.toEntity(createParticipantRequest);
        System.out.println("ICI \n \n \n ============ \n " + newParticipant);
        participantComponent.createParticipant(newParticipant);
    }

    /**
     * @param id de l'entité Participant à supprimer
     */
    @Transactional
    public void deleteParticipant(Long id) {
        try {
            participantComponent.deleteParticipant(id);
        } catch (ParticipantEntityNotFoundException e) {
            throw new ParticipantEntityNotDeletedRestException(e.getMessage());
        }
    }

}