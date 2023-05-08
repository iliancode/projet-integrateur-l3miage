package fr.uga.l3miage.example.component;

import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.EnseignantEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.QuestionEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.ReponseEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.EnseignantEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.QuestionEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.TestEntityNotFoundException;
import fr.uga.l3miage.example.models.Enseignant;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.repository.EnseignantRepository;
import fr.uga.l3miage.example.repository.MiahootRepository;
import fr.uga.l3miage.example.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionComponent {
    private final MiahootComponent miahootComponent;
    private final EnseignantRepository enseignantRepository;
    private final MiahootRepository miahootRepository;
    private final QuestionRepository questionRepository;


    public void createQuestionInMiahoot(final Long idEnseignant, final Long idMiahoot, final Question question) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new TestEntityNotFoundException(String.format("Aucune entité n'a été trouvé pour l'id [%s]", idEnseignant), "" + idEnseignant));

        Miahoot m = miahootRepository.findById(idMiahoot)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id "));

        if (e.containsMiahoot(idMiahoot)) {

            questionRepository.save(question);

            e.getMiahoot(idMiahoot).getQuestions().add(question);
            enseignantRepository.save(e);
            miahootRepository.save(m);


        } else {
            throw new Exception("L'enseignant n'a pas le droit de modifier ce miahoot");
        }
    }


    public List<Question> getAllQuestionsOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws Exception {
        Enseignant e = enseignantRepository.findById(idEnseignant)
                .orElseThrow(() -> new Exception("Aucune entité n'a été trouvé pour l'id"));

        if (e.containsMiahoot(idMiahoot)) {
            return e.getMiahoot(idMiahoot).getQuestions();
        } else {
            throw new Exception("L'enseignant n'a pas le droit de modifier ce miahoot");
        }
    }


    public Question getQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion) throws QuestionEntityNotFoundException{
        try {
            Miahoot miahoot = miahootComponent.getMiahootOfEnseignant(idEnseignant, idMiahoot);
            Question question = questionRepository.findById(idQuestion)
                    .orElseThrow(() -> new QuestionEntityNotFoundException(String.format("L'entité Question n'a pas été trouvée pour l'id [%s]", idQuestion), idQuestion));
            if (miahoot.getQuestion(idQuestion).equals(question)) {
                return question;
            } else {
                throw new QuestionEntityNotFoundException(String.format("La question [%s] n'appartient pas au miahoot [%s] de l'enseignant [%s]", idQuestion, idMiahoot, idEnseignant), idQuestion);
            }
        } catch (MiahootEntityNotFoundException e) {
            throw new QuestionEntityNotFoundException(e.getMessage(), idMiahoot, e);
        }
    }


    public void deleteQuestionOfMiahoot(Miahoot miahoot, Question question) {
        miahoot.getQuestions().remove(question);
        questionRepository.delete(question);
    }

}
