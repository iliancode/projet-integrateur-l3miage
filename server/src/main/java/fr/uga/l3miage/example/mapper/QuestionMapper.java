package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.request.CreateQuestionRequest;
import fr.uga.l3miage.example.response.QuestionDTO;
import org.mapstruct.Mapper;

@Mapper
public interface QuestionMapper {
    Question toEntity(CreateQuestionRequest request);
    // @Mapping(source = "miahoots",target = "miahootsDTO",qualifiedBy = TestMapperUtils.ToSumTestInt.class)
    QuestionDTO toDto(Question questionEntity);
}
