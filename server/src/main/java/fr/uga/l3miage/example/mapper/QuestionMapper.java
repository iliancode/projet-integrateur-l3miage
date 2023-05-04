package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.QuestionDTO;
import fr.uga.l3miage.example.response.ReponseDTO;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface QuestionMapper {

    // @Mapping(source = "miahoots",target = "miahootsDTO",qualifiedBy = TestMapperUtils.ToSumTestInt.class)
    QuestionDTO toDto(Question questionEntity);

    Question toQuestion(CreateQuestionRequest request);
    List<Question> toQuestionList(List<QuestionDTO> request);

    ReponseDTO map(Reponse reponse);

    void mergeQuestionEntity(@MappingTarget @NonNull Question ancienQuestion, QuestionDTO newQuestion);

}
