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

@Mapper(uses = ReponseMapper.class)
public interface QuestionMapper {

    QuestionDTO toDto(Question question);
    List<QuestionDTO> toDto(List<Question> questions);

    Question toEntity(CreateQuestionRequest request);

    ReponseDTO map(Reponse reponse);

}
