package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.ParticipantEntityNotFoundException;
import fr.uga.l3miage.example.mapper.ParticipantMapper;
import fr.uga.l3miage.example.models.Participant;
import fr.uga.l3miage.example.models.TestEntity;
import fr.uga.l3miage.example.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantComponent {
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    /**
     * @param id de l'entité Test à récupérer
     * @return une {@link TestEntity} correspondant à description donnée
     * @throws ParticipantEntityNotFoundException si aucune entité Participant n'est trouvée
     */
    public Participant getParticipant(final Long id) throws ParticipantEntityNotFoundException {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ParticipantEntityNotFoundException(String.format("Aucune entité Participant n'a été trouvée pour l'id [%l]", id), id));
    }

    /**
     * @param entity à créer en base de données
     */
    public void createParticipant(final Participant entity) {
        System.out.println("ICI : participant component");
        participantRepository.save(entity);
    }

    /**
     * @param id de l'entité à supprimer
     * @throws ParticipantEntityNotFoundException si l'entité avec l'id fournit n'est pas trouvée
     */
    public void deleteParticipant(final Long id) throws ParticipantEntityNotFoundException {
        try {
            Participant deleted = getParticipant(id);
            participantRepository.deleteById(id);
        } catch (ParticipantEntityNotFoundException e) {
            throw new ParticipantEntityNotFoundException("L'entité Participant à supprimer n'a pas été trouvée", id);
        }
    }

}
