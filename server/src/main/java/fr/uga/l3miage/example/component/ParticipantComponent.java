package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.entityNotFoundException.ParticipantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.ParticipantMapper;
import fr.uga.l3miage.example.models.Participant;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.models.TestEntity;
import fr.uga.l3miage.example.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ParticipantComponent {
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;


    /**
     * @param entity à créer en base de données
     */
    public void createParticipantByPartie(final Partie partie, final Participant entity) {
        entity.setPartie(partie);
        participantRepository.save(entity);
    }

    public List<Participant> getAllParticipantsOfPartie(Partie partie) {
        return participantRepository.findAllByPartie(partie);
    }

}
