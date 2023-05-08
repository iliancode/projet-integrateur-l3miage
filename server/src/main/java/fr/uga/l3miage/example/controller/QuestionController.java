package fr.uga.l3miage.example.controller;

import fr.uga.l3miage.example.endpoint.QuestionEndpoint;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.QuestionDTO;
import fr.uga.l3miage.example.response.ReponseDTO;
import fr.uga.l3miage.example.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController implements QuestionEndpoint {
    private final QuestionService questionService;


    @Override
    public void addQuestionToMiahoot(Long idEnseignant, Long idMiahoot, CreateQuestionRequest createQuestionRequest) throws Exception {
        questionService.addQuestionToMiahoot(idEnseignant, idMiahoot , createQuestionRequest);
    }

    //get all question of a miahoot of an enseignant
    @Override
    public List<QuestionDTO> getAllQuestionsOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot) throws Exception {
        return questionService.getAllQuestionsOfMiahootOfEnseignant(idEnseignant, idMiahoot);
    }

    @Override
    public QuestionDTO getQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion) {
        return questionService.getQuestionOfMiahootOfEnseignant(idEnseignant,idMiahoot,idQuestion);
    }


    @Override
    public void deleteQuestionOfMiahootOfEnseignant(Long idEnseignant, Long idMiahoot, Long idQuestion) {
        questionService.deleteQuestionOfMiahootOfEnseignant(idEnseignant,idMiahoot,idQuestion);
    }
}
