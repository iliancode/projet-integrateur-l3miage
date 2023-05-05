package fr.uga.l3miage.example.component;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.QuestionEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.ReponseEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.QuestionEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.ReponseEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.TestEntityNotFoundException;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.repository.EnseignantRepository;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.repository.QuestionRepository;
import fr.uga.l3miage.example.repository.ReponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import fr.uga.l3miage.example.mapper.ReponseMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReponseComponent {
    private final EnseignantRepository enseignantRepository;
    private final MiahootRepository miahootRepository;
    private final QuestionRepository questionRepository;
    private final ReponseRepository reponseRepository;
    private final QuestionComponent questionComponent;


    public void addReponseToQuestionOfMiahoot(final Long idEnseignant, final Long idMiahoot, final Long idQuestion, final Reponse newReponse) throws Exception {

        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idEnseignant), "" + idEnseignant));

        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id "));

        Question q = questionRepository.findById(idQuestion)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id "));

        if (e.containsMiahoot(idMiahoot)) {

            if (m.containsQuestion(idQuestion)) {
                reponseRepository.save(newReponse);
                e.getMiahoot(idMiahoot).getQuestion(idQuestion).getReponses().add(newReponse);
                questionRepository.save(q);
            } else {
                throw new Exception("Question non contenu dans ce miahoot");
            }
        } else {
            throw new Exception("L'enseignant n'a pas le droit de modifier ce miahoot");
        }
    }


    public List<Reponse> getAllReponsesOfQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idEnseignant), "" + idEnseignant));

        if (e.containsMiahoot(idMiahoot)) {
            if (e.getMiahoot(idMiahoot).containsQuestion(idQuestion)) {

                return e.getMiahoot(idMiahoot).getQuestion(idQuestion).getReponses();
            } else {
                throw new Exception("Question non contenu dans ce miahoot");
            }

        } else {
            throw new Exception("L'enseignant n'a pas le droit de modifier ce miahoot");
        }
    }


    public Reponse getReponseOfQuestionOfMiahootOfEnseignant(final Long idEnseignant, final Long idMiahoot, final Long idQuestion, final Long idReponse) {
        try {
            questionComponent.getQuestionOfMiahootOfEnseignant(idEnseignant, idMiahoot, idQuestion);
            return reponseRepository.findById(idReponse)
                    .orElseThrow(() -> new ReponseEntityNotFoundRestException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idReponse), idReponse));
        } catch (EnseignantEntityNotFoundException | MiahootEntityNotFoundException | QuestionEntityNotFoundException e) {
            throw new ReponseEntityNotFoundRestException("Aucune entité n'a été trouvé pour cet utilisateur", idQuestion);
        }

    }


    public void deleteReponse(Long idReponse) {
        reponseRepository.deleteById(idReponse);
    }

}
