package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.response.QuestionDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface QuestionMapper {
    QuestionDTO entityToDTO(Question question);

    Collection<QuestionDTO> entityToDTO(Iterable<Question> questions);

    Question dtoToEntity(QuestionDTO question);

    Collection<Question> dtoToEntity(Iterable<QuestionDTO> questions);
}
