package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.MiahootComponent;
import fr.uga.l3miage.example.component.QuestionComponent;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.QuestionEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotDeletedRestException.ReponseEntityNotDeletedRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.QuestionEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.rest.entityNotFoundRestException.ReponseEntityNotFoundRestException;
import fr.uga.l3miage.example.exception.technical.MiahootEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.QuestionEntityNotFoundException;
import fr.uga.l3miage.example.exception.technical.entityNotFoundException.ReponseEntityNotFoundException;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.QuestionDTO;
import fr.uga.l3miage.example.response.ReponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final MiahootComponent miahootComponent;
    private final QuestionComponent questionComponent;
    private final QuestionMapper questionMapper;


    public void addQuestionToMiahoot(final String uidEnseignant, final Long idMiahoot, final CreateQuestionRequest createQuestionRequest) throws Exception {
        Question newQuestion = questionMapper.toQuestion(createQuestionRequest);
        questionComponent.createQuestionInMiahoot(uidEnseignant, idMiahoot, newQuestion);

    }

    public List<QuestionDTO> getAllQuestionsOfMiahootOfEnseignant(String uidEnseignant, Long idMiahoot) throws Exception {
        return questionMapper.toQuestionListDto(questionComponent.getAllQuestionsOfMiahootOfEnseignant(uidEnseignant, idMiahoot));
    }


    public QuestionDTO getQuestionOfMiahootOfEnseignant(String uidEnseignant, Long idMiahoot, Long idQuestion) {
        try {
            return questionMapper.toDto(questionComponent.getQuestionOfMiahootOfEnseignant(uidEnseignant, idMiahoot, idQuestion));
        } catch (QuestionEntityNotFoundException e) {
            throw new QuestionEntityNotFoundRestException(e.getMessage(), idQuestion, e);
        }
    }


    public void deleteQuestionOfMiahootOfEnseignant(String uidEnseignant, Long idMiahoot, Long idQuestion) {
        try {
            Miahoot miahoot = miahootComponent.getMiahootOfEnseignant(uidEnseignant, idMiahoot);
            Question question = questionComponent.getQuestionOfMiahootOfEnseignant(uidEnseignant, idMiahoot, idQuestion);
            questionComponent.deleteQuestionOfMiahoot(miahoot, question);
        } catch (MiahootEntityNotFoundException | QuestionEntityNotFoundException e) {
            throw new QuestionEntityNotDeletedRestException(e.getMessage(), e);
        }
    }
}
