package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.technical.entityNotFoundException.ParticipantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.isNotEntityOf.IsNotPartieOfEnseignantException;
import fr.uga.l3miage.example.mapper.ParticipantMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.models.Participant;
import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.repository.EnseignantRepository;
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
    private final EnseignantRepository enseignantRepository;


    /**
     * @param entity à créer en base de données
     */
    public void createParticipantByPartie(final Partie partie, final Participant entity) {
        entity.setPartie(partie);
        participantRepository.save(entity);
    }


    /**
     * @param partie la partie à laquelle appartiennent les participants
     * @return la liste des participants de la partie
     */
    public List<Participant> getAllParticipantsOfPartie(Partie partie) {
        return participantRepository.findAllByPartie(partie);
    }


    /**
     * @param partie la partie à laquelle appartient le participant
     * @param id l'id du participant à récupérer
     * @return le participant de la partie
     * @throws ParticipantEntityNotFoundException si le participant n'existe pas
     */
    public Participant getParticipantOfPartie(Partie partie, Long id) throws ParticipantEntityNotFoundException {
        return participantRepository.findByPartieAndId(partie, id)
                .orElseThrow(() -> new ParticipantEntityNotFoundException(String.format("Aucune entité Participant n'a été trouvée pour l'id [%d]", id), id));
    }


    /**
     * @param uidEnseignant l'id de l'enseignant
     * @param partie la partie à laquelle appartient le participant
     * @throws EnseignantEntityNotFoundException si l'enseignant n'existe pas
     */
    public void deleteAllParticipantsFromPartie(String uidEnseignant, Partie partie) throws EnseignantEntityNotFoundException, IsNotPartieOfEnseignantException {
        boolean isPartieofEnseignant = false;

        Enseignant enseignant = enseignantRepository.findByUid(uidEnseignant)
                .orElseThrow(() -> new EnseignantEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", uidEnseignant), uidEnseignant));

        for (Partie p : enseignant.getParties()) {
            if (partie.getCodePartie() == p.getCodePartie()) {
                isPartieofEnseignant = true;
                break;
            }
        }

        if (!isPartieofEnseignant) {
            throw new IsNotPartieOfEnseignantException(String.format("La partie [%s] n'appartient pas à l'enseignant [%s]", uidEnseignant, partie.getCodePartie()), uidEnseignant, partie.getCodePartie());
        }

        List<Participant> participants = participantRepository.findAllByPartie(partie);
        participantRepository.deleteAll(participants);
    }

}
