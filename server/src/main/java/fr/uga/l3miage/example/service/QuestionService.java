package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.QuestionComponent;
import fr.uga.l3miage.example.mapper.QuestionMapper;
import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.QuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionComponent questionComponent;
    private final QuestionMapper questionMapper;


    public void addQuestionToMiahoot(final Long idEnseignant, final Long idMiahoot, final CreateQuestionRequest createQuestionRequest) throws Exception {
        Question newQuestion = questionMapper.toQuestion(createQuestionRequest);
        questionComponent.createQuestionInMiahoot(idEnseignant, idMiahoot, newQuestion);

    }

    public List<QuestionDTO> getAllQuestionsOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws Exception {
        return questionMapper.toQuestionListDto(questionComponent.getAllQuestionsOfMiahootOfEnseignant(idEnseignant, idMiahoot));
    }
}
