package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.MiahootEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.ReponseEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.TestEntityNotFoundException;
import fr.uga.l3miage.example.mapper.MiahootMapper;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.repository.EnseignantRepository;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.repository.QuestionRepository;
import fr.uga.l3miage.example.request.CreateFullMiahootRequest;
import fr.uga.l3miage.example.response.QuestionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MiahootComponent {
    private final EnseignantComponent enseignantComponent;
    private final EnseignantRepository enseignantRepository;
    private final MiahootRepository miahootRepository;
    private final MiahootComponent miahootComponent;


    public void createMiahootFromEnseignant(final String uidEnseignant, final Miahoot miahoot) throws Exception {
        try {
            Enseignant enseignant = enseignantRepository.findByUid(uidEnseignant)
                    .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", uidEnseignant), uidEnseignant));
            enseignant.getMiahoots().add(miahoot);
        } catch (Exception ex) {
            throw new Exception("Impossible de créer le Miahoot et de l'ajouter. Raison :" + ex.getMessage());
        }
    }

    public List<Miahoot> getAllMiahootsOfEnseignant(String uidEnseignant) throws Exception {
        Enseignant e = enseignantRepository.findByUid(uidEnseignant)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id "));

        return e.getMiahoots();
    }

    public Miahoot getMiahootOfEnseignant(String uidEnseignant, Long idMiahoot) throws MiahootEntityNotFoundException {
        try {
            Enseignant enseignant = enseignantComponent.getEnseignantByUid(uidEnseignant);
            Miahoot miahoot =  miahootRepository.findById(idMiahoot)
                    .orElseThrow(() -> new MiahootEntityNotFoundException(String.format("Aucun miahoot n'a été trouvé pour l'id [%s]", idMiahoot), idMiahoot));
            if (enseignant.getMiahoot(idMiahoot).equals(miahoot)) {
                return miahoot;
            } else {
                throw new MiahootEntityNotFoundException(String.format("Le miahoot [%s] n'appartient pas à l'enseignant [%s]", idMiahoot, uidEnseignant), idMiahoot);
            }
        } catch (EnseignantEntityNotFoundException e) {
            throw new MiahootEntityNotFoundException(e.getMessage(), idMiahoot, e);
        }
    }

    public void deleteMiahootOfEnseignant(final Enseignant enseignant, final Miahoot miahoot) {
        enseignant.getMiahoots().remove(miahoot);
        miahootRepository.delete(miahoot);
    }

    public void createMiahootOfEnseignant(final String uidEnseignant, final Miahoot newMiahoot) throws EnseignantEntityNotFoundException {
        Enseignant enseignant = enseignantComponent.getEnseignantByUid(uidEnseignant);
        enseignant.getMiahoots().add(newMiahoot);
        miahootRepository.save(newMiahoot);
    }


    public void updateFullMiahootOfEnseignant(final String uidEnseignant, final Long idMiahoot, final Miahoot newMiahoot) throws EnseignantEntityNotFoundException, MiahootEntityNotFoundException {
        Enseignant enseignant = enseignantComponent.getEnseignantByUid(uidEnseignant);
        Miahoot miahoot = miahootComponent.getMiahootOfEnseignant(uidEnseignant, idMiahoot);
        enseignant.getMiahoots().remove(miahoot);
        enseignant.getMiahoots().add(newMiahoot);
        miahootRepository.save(newMiahoot);
    }
}
