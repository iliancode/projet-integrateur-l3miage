package fr.uga.l3miage.example.component;

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
    private final EnseignantRepository enseignantRepository;
    private final MiahootRepository miahootRepository;
    private final QuestionMapper questionMapper;


    public void createMiahootFromEnseignant(final String uidEnseignant, final Miahoot miahoot) throws Exception {
        try {
            Enseignant enseignant = enseignantRepository.findByUid(uidEnseignant)
                    .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", uidEnseignant), "" + uidEnseignant));
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

    public Miahoot getMiahootOfEnseignant(String uidEnseignant, Long idMiahoot) throws Exception {
        Enseignant e = enseignantRepository.findByUid(uidEnseignant)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id "));
        return e.getMiahoot(idMiahoot);
    }

    public void deleteMiahootOfEnseignant(final Long idEnseignant, final Long idMiahoot) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvée pour l'id [%s]", idEnseignant), "" + idEnseignant));

        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvée pour le mail [%s]", idMiahoot), "erreur"));

        if (e.containsMiahoot(idMiahoot)) {
            e.removeMiahoot(m);
            enseignantRepository.save(e);
            miahootRepository.delete(m);
        }
    }

    public void createMiahootOfEnseignant(String uidEnseignant, CreateFullMiahootRequest createFullMiahootRequest) throws Exception {
        Enseignant e = enseignantRepository.findByUid(uidEnseignant)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour le mail "));
        Miahoot miahoot = new Miahoot();
        miahoot.setNom(createFullMiahootRequest.getNom());
        miahoot.setQuestions(questionMapper.toQuestionList( createFullMiahootRequest.getQuestions()));
        e.getMiahoots().add(miahoot);
        miahootRepository.save(miahoot);

    }
}
